package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Mileage;
import com.younghun.hibusgo.domain.Payment;
import com.younghun.hibusgo.domain.PaymentMeans;
import com.younghun.hibusgo.domain.PaymentMeansType;
import com.younghun.hibusgo.domain.PaymentStatus;
import com.younghun.hibusgo.domain.Reservation;
import com.younghun.hibusgo.dto.PaymentDto;
import com.younghun.hibusgo.dto.ReservationDto;
import com.younghun.hibusgo.mapper.MileageMapper;
import com.younghun.hibusgo.mapper.PaymentMapper;
import com.younghun.hibusgo.mapper.ReservationMapper;
import com.younghun.hibusgo.mapper.SeatMapper;
import com.younghun.hibusgo.utils.PaymentMeansFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationMapper reservationMapper;
  private final PaymentMapper paymentMapper;
  private final SeatMapper seatMapper;
  private final MileageMapper mileageMapper;

  private final PaymentMeansFactory paymentMeansFactory;

  public Optional<Reservation> findById(long id) {
    return Optional.ofNullable(reservationMapper.findById(id))
        .filter(o -> o.getStatus() != DataStatus.DELETED);
  }

  public List<Reservation> findByAccountId(long id) {
    return reservationMapper.findByAccountId(id);
  }

  @Transactional
  public void addReservation(PaymentDto paymentDto) {
    long routeId = paymentDto.getRouteId(); // 노선 아아디
    long accountId = paymentDto.getAccountId(); // 회원 아이디
    long seatId = paymentDto.getRouteId(); // 좌석 아이디
    long seatNumber = paymentDto.getSeatNumber(); // 좌석 번호

    PaymentMeansType meansType = paymentDto.getMeans();

    // 좌석 상태(사용중) 변경
    seatMapper.updateStatus(seatId, seatNumber);

    // 결제 객체 생성
    Payment payment = paymentDto.toEntity();

    // 결제 상태 전달
    final PaymentStatus paymentStatus = paymentMeansFactory.getStatus(meansType);
    payment.transStatus(paymentStatus);

    // 결제 추가(무통장 입금은 결제 대기 상태)
    long paymentId = paymentMapper.addPayment(payment);

    // 예매 정보에 결제 아이디 전달
    ReservationDto reservationDto = new ReservationDto();
    Reservation reservation = reservationDto.transIds(routeId, accountId, paymentId);

    // 결제 수단 정보 생성
    PaymentMeans paymentMeans = paymentMeansFactory.getMeans(paymentId, meansType, paymentDto);

    // 결제 수단 추가
    final PaymentMeansService paymentMeansService = paymentMeansFactory.getType(meansType);
    paymentMeansService.addPaymentMeans(paymentMeans);

    // 예매 추가
    reservationMapper.addReservation(reservation);

    // 이전 마일리지 조회
    Mileage mileage = mileageMapper.findByAccountId(accountId);
    long beforeMileage = mileage.getMileage();

    // 마일리지 계산
    long charge = payment.getCharge();
    long calculateMileage = calculateMileage(charge, beforeMileage);

    // 마일리지 적립 금액이 0원 보다 큰 경우에 적립
    if (calculateMileage > 0) {
      mileage = paymentDto.toEntityOfMileage(calculateMileage);
      mileageMapper.updateMileage(mileage);
    }

  }

  public boolean existsById(long id) {
    return reservationMapper.existsById(id);
  }

  @Transactional
  public void deleteReservation(long id) {
    Reservation reservation = reservationMapper.findById(id);

    long paymentId = reservation.getPaymentId();

    Payment payment = paymentMapper.findById(id);
    PaymentMeansType meansType = payment.getMeans();

    // 결제 수단 삭제
    final PaymentMeansService paymentMeansService = paymentMeansFactory.getType(meansType);
    paymentMeansService.deletePaymentMeansByPaymentId(paymentId);

    // 결제 삭제
    paymentMapper.deletePayment(id);

    // 예약 삭제
    reservationMapper.deleteReservation(id);

  }

  public boolean existsByIdAndAccountId(long reservationId, long accountId) {
    return reservationMapper.existsByIdAndAccountId(reservationId, accountId);
  }

  public long calculateMileage(long charge, long beforeMileage) {
    return (long) (charge * 0.001) + beforeMileage;
  }
}