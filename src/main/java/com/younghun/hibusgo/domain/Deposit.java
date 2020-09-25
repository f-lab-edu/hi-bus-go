package com.younghun.hibusgo.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString
public class Deposit extends PaymentMeans {

  // 입금 계좌 번호
  private String accountNumber;

  // 입금 은행
  private String accountName;
}
