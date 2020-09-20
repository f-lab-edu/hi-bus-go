package com.younghun.hibusgo.controller;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Reservation;
import com.younghun.hibusgo.service.ReservationService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class ReservationController {

  private final ReservationService reservationService;

  /**
   * 특정 회원의 예매 목록 조회 메서드
   * @param id 회원의 아이디
   * @return List<Reservation>
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @GetMapping("/{id}")
  public ResponseEntity<List<Reservation>> getReservations(@PathVariable long id) {
    List<Reservation> reservations = reservationService.findByAccountId(id);

    return ResponseEntity.ok().body(reservations);
  }

  /**
   * 특정 회원의 예매 정보 조회 메서드
   * @param id 예매 아이디
   * @return ResponseEntity<?>
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @GetMapping("/account")
  public ResponseEntity<?> getReservation(@RequestParam long id) {
    Optional<Reservation> reservation = reservationService.findById(id);

    return ResponseEntity.ok().body(reservation.get());
  }

}
