package com.younghun.hibusgo.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Config {

  // 아이디
  private long id;

  // 마일리지 적립 비율
  private long mileageRate;

  // 추가일
  private LocalDateTime createdAt;

  // 수정일
  private LocalDateTime updatedAt;

}
