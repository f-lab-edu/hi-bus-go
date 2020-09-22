package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.Seat;
import com.younghun.hibusgo.domain.SeatGrade;
import com.younghun.hibusgo.domain.SeatStatus;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SeatDto {

  //노선 아이디
  @NotBlank
  private long routeId;

  // 좌석 번호
  @NotBlank
  private long number;

  // 좌석 상태
  @NotBlank
  private SeatStatus status;

  public Seat toEntity() {
    return Seat.builder()
        .routId(this.routeId)
        .number(this.number)
        .status(this.status)
        .build();
  }

}
