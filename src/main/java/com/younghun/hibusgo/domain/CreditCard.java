package com.younghun.hibusgo.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreditCard implements PaymentMeans {

  // 결제 수단 아이디
  private long id;

  // 결제 아이디
  private long paymentId;

  // 카드 번호
  private String cardNumber;

  // 카드 회사 이름
  private String cardName;

  // 상태
  private DataStatus status;

  // 결제 수단 추가일
  private LocalDateTime createdAt;

  // 결제 수단 수정일
  private LocalDateTime updatedAt;

}
