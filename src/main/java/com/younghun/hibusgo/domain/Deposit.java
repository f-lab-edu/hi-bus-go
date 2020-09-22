package com.younghun.hibusgo.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Deposit implements PaymentMeans {

  // 결제 수단 아이디
  private long id;

  // 결제 아이디
  private long paymentId;

  // 입금 계좌 번호
  private String accountNumber;

  // 입금 은행
  private String accountName;

  // 상태
  private DataStatus status;

  // 결제 수단 추가일
  private LocalDateTime createdAt;

  // 결제 수단 수정일
  private LocalDateTime updatedAt;

}
