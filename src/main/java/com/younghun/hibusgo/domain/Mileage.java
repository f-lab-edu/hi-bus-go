package com.younghun.hibusgo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Mileage {

  //아이디
  private int id;

  //회원 아이디
  private int accountId;

  //마일리지
  private int mileage;

  //상태
  private DataStatus status;

}
