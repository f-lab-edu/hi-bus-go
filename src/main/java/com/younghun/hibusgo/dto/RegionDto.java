package com.younghun.hibusgo.dto;


import com.younghun.hibusgo.domain.Region;
import com.younghun.hibusgo.domain.Region.Status;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * request Body에 있는 json data를 mapping하기 위해, 생성자 추가
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RegionDto {

    @NotBlank
    private String name;

    public Region toEntity() {
        return Region.builder()
            .name(this.name)
            .status(Status.DEFAULT)
            .build();
    }

}