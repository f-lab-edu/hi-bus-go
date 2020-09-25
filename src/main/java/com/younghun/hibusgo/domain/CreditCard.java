package com.younghun.hibusgo.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @SuperBuilder : 기존 @Builder은 상속 관계에서 사용시, 부모가 가진 변수를 활용하여 객체 생성을 하지 못한다.
 * @SuperBuilder은 상속 관계에서도 부모가 가진 변수를 활영하여 객체 생성을 만들도록 한다.
 * 부모 및 상속 받는 자식 둘다 사용해야 상속 관계에서 사용가능하다.
 */
@Getter
@SuperBuilder
@ToString
public class CreditCard extends PaymentMeans {

  // 카드 번호
  private String cardNumber;

  // 카드 회사 이름
  private String cardName;

}
