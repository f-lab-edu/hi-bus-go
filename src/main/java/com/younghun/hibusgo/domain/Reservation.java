package com.younghun.hibusgo.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Reservation {

  //아이디
  private long id;

  //노선 아이디
  private long routeId;

  //노선 이름
  private String routeName;

  //노선 소요 시간
  private String timeRequired;

  //회원 아이디
  private long accountId;

  //결제 아이디
  private long paymentId;

  //좌석 아이디
  private long seadId;

  //좌석 번호
  private long seatNumber;

  //좌석 등급
  private SeatGrade grade;

  //요금
  private long fare;

  //예매 추가일
  private LocalDateTime createdAt;

  //예매 수정일
  private LocalDateTime updatedAt;

  //상태
  private DataStatus status;

}
