package com.younghun.hibusgo.domain;

import java.time.LocalDateTime;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class PaymentMeans {

  // 결제 수단 아이디
  private long id;

  // 결제 아이디
  private long paymentId;

  // 상태
  private DataStatus status;

  // 결제 수단 추가일
  private LocalDateTime createdAt;

  // 결제 수단 수정일
  private LocalDateTime updatedAt;
}
