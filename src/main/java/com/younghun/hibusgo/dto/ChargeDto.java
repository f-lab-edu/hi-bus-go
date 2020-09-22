package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.Charge;
import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.RouteGrade;
import com.younghun.hibusgo.domain.SeatGrade;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChargeDto {

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

  public Charge toEntity() {
    return Charge.builder()
        .id(this.id)
        .routId(this.routId)
        .routeGrade(this.routeGrade)
        .seatGrade(this.seatGrade)
        .status(DataStatus.DEFAULT)
        .build();
  }

}
