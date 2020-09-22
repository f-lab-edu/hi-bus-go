package com.younghun.hibusgo.domain;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Charge {

  // 아이디
  private long id;

  // 노선 아이디
  @NotBlank
  private long routId;

  // 노선 등급
  @NotBlank
  RouteGrade routeGrade;

  // 좌석 등급
  @NotBlank
  SeatGrade seatGrade;

  // 요금
  @NotBlank
  private long charge;

  // 상태
  DataStatus status;

}
