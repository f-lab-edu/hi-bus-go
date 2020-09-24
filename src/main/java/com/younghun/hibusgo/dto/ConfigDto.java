package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.Config;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ConfigDto {

  // 아이디
  private long id;

  // 마일리지 적립 비율
  private long mileageRate;

  public Config toEntity() {
    return Config.builder()
        .id(this.id)
        .mileageRate(this.mileageRate)
        .build();
  }

}
