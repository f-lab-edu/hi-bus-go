package com.younghun.hibusgo.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Reservation {

  // 아이디
  private long id;

  // 노선 아이디
  private long routeId;

  // 노선 이름
  private String routeName;

  // 노선 등급
  private RouteGrade routeGrade;

  // 노선 소요 시간
  private String timeRequired;

  // 회원 아이디
  private long accountId;

  // 결제 아이디
  private long paymentId;

  // 좌석 번호
  private long seatNumber;

  // 좌석 상태
  private SeatStatus seatStatus;

  // 좌석 등급
  private SeatGrade grade;

  // 예매 추가일
  private LocalDateTime createdAt;

  // 예매 수정일
  private LocalDateTime updatedAt;

  // 상태
  private DataStatus status;

}
