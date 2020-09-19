package com.younghun.hibusgo.controller;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Payment;
import com.younghun.hibusgo.service.PaymentService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {

  private final PaymentService paymentService;

  /**
   * 특정 회원의 결제 목록 조회 메서드
   * @param id 회원의 아이디
   * @return List<Payment>
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @GetMapping("/{id}")
  public ResponseEntity<List<Payment>> getPayments(@PathVariable long id) {
    List<Payment> payments = paymentService.findByAccountId(id);

    return ResponseEntity.ok().body(payments);
  }

  /**
   * 회원의 결제 정보 조회 메서드
   * @param id 결제 아이디
   * @return ResponseEntity<?>
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @GetMapping("/account/{id}")
  public ResponseEntity<?> getPayment(@PathVariable long id) {
    Optional<Payment> payment = paymentService.findById(id);

    return ResponseEntity.ok().body(payment.get());
  }


}
