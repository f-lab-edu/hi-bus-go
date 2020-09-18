package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Payment;
import com.younghun.hibusgo.domain.PaymentMeansType;
import com.younghun.hibusgo.domain.Reservation;
import com.younghun.hibusgo.domain.RouteGrade;
import com.younghun.hibusgo.dto.PaymentDto;
import com.younghun.hibusgo.dto.PaymentMeansDto;
import com.younghun.hibusgo.dto.ReservationDto;
import com.younghun.hibusgo.mapper.ReservationMapper;
import com.younghun.hibusgo.mapper.SeatMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationMapper reservationMapper;
  private final PaymentService paymentService;
  private final PaymentMeansService paymentMeansService;
  private final SeatMapper seatMapper;

  public Optional<Reservation> findById(long id) {
    return Optional.ofNullable(reservationMapper.findById(id))
        .filter(o -> o.getStatus() != DataStatus.DELETED);
  }

  public List<Reservation> findByAccountId(long id) {
    return reservationMapper.findByAccountId(id);
  }

  @Transactional
  public void addReservation(ReservationDto reservationDto, PaymentDto paymentDto, PaymentMeansDto paymentMeansDtoDto) {
    PaymentMeansType meansType = paymentDto.getMeans();

    long routeId = reservationDto.getRouteId();
    long seatNumber = reservationDto.getSeatNumber();

    RouteGrade routeGrade = reservationDto.getRouteGrade();

    // 좌석 상태(사용중) 변경
    seatMapper.updateStatus(routeId, seatNumber);

    // 결제 금액 계산
    long payCharge = calculatePaymentCharge(routeGrade);

    // 결제 금액 전달
    paymentDto.transPayCharge(payCharge);
    paymentDto.updateStatus(meansType);
    Payment payment = paymentDto.toEntity();

    // 결제 추가(무통장 입금은 결제 대기 상태)
    long paymentId = paymentService.addPayment(payment);

    // 예매 정보에 결제 아이디 전달
    reservationDto.transPaymentId(paymentId);
    Reservation reservation = reservationDto.toEntity();

    // 결제 수단 정보에 결제 아이디 전달
    paymentMeansDtoDto.transPaymentId(paymentId);

    // 결제 수단 추가
    paymentMeansService.addPaymentMeans(meansType, paymentMeansDtoDto);

    // 예매 추가
    reservationMapper.addReservation(reservation);
  }

  public long calculatePaymentCharge(RouteGrade routeGrade) {
    long payCharge = 0;

    if (RouteGrade.PREMIUM == routeGrade) {
      payCharge = 30000;
    }
    else if (RouteGrade.FIRST == routeGrade) {
      payCharge = 20000;
    }
    else if (RouteGrade.ECONOMY == routeGrade) {
      payCharge = 10000;
    }

    return payCharge;
  }

}
