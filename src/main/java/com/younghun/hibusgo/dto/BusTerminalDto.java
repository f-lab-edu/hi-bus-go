package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.BusTerminal;

import com.younghun.hibusgo.domain.DataStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BusTerminalDto {

    // 아이디
    private long id;

    // 터미널 이름
    @NotBlank
    @Max(255)
    private String name;

    // 주소
    @NotBlank
    @Max(255)
    private String address;

    // 전화번호
    @NotBlank
    @Pattern(regexp = "/^\\d{2,3}-\\d{3,4}-\\d{4}$/")
    private String tel;

    // 지역 아이디
    @NotBlank
    private long regionId;

    // 버스 터미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private DataStatus status;

    public BusTerminal toEntity() {
        return BusTerminal.builder()
            .name(this.name)
            .address(this.address)
            .tel(this.tel)
            .regionId(this.regionId)
            .status(DataStatus.DEFAULT)
            .build();
    }
}
