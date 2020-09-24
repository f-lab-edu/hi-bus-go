package com.younghun.hibusgo.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString
public class KakaoPay extends PaymentMeans {

  // 카드 번호
  private String cardNumber;

  // 카드 회사 이름
  private String cardName;
}
