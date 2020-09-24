package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Payment;
import com.younghun.hibusgo.domain.PaymentMeans;
import com.younghun.hibusgo.domain.PaymentMeansType;
import com.younghun.hibusgo.domain.PaymentStatus;
import com.younghun.hibusgo.domain.Reservation;
import com.younghun.hibusgo.dto.PaymentDto;
import com.younghun.hibusgo.dto.ReservationDto;
import com.younghun.hibusgo.mapper.PaymentMapper;
import com.younghun.hibusgo.mapper.ReservationMapper;
import com.younghun.hibusgo.mapper.SeatMapper;
import com.younghun.hibusgo.utils.CacheKeys;
import com.younghun.hibusgo.utils.PaymentMeansFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationMapper reservationMapper;
  private final PaymentMapper paymentMapper;
  private final SeatMapper seatMapper;

  private final PaymentMeansFactory paymentMeansFactory;

  public Optional<Reservation> findById(long id) {
    return Optional.ofNullable(reservationMapper.findById(id))
        .filter(o -> o.getStatus() != DataStatus.DELETED);
  }

  /**
   * @Cacheable : 동일 값이 Cache에 있는 경우 Cache에서 데이터를 return합니다.
   * 만약 동일 key 값이 없을 경우 메소드를 실행하고 반환된 결과 값을 Cache에 저장합니다.
   * unless = "#result == null" -> 조회 결과 값이 null이 아닐 경우에만 캐싱
   */
  @Cacheable(value = CacheKeys.RESERVATION_ACCOUNT, key = "#id", unless = "#result == null" ,cacheManager = "redisCacheManager")
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
  }

}