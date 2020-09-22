package com.younghun.hibusgo.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Seat {

  // 아이디
  private long id;

  // 노선 아이디
  private long routId;

  // 좌석 번호
  private long number;

  // 좌석 상태
  private SeatStatus status;

  // 추가일
  private LocalDateTime createdAt;

  // 수정일
  private LocalDateTime updatedAt;

}
