package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.domain.Status;
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

    //터미널 이름
    @NotBlank
    private String name;

    //주소
    @NotBlank
    private String address;

    //전화번호
    @NotBlank
    @Pattern(regexp = "/^\\d{2,3}-\\d{3,4}-\\d{4}$/")
    private String tel;

    //지역 이름
    @NotBlank
    private String region;

    // 버스 미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private Status status;

    public BusTerminal toEntity() {
        return BusTerminal.builder()
            .name(this.name)
            .address(this.address)
            .tel(this.tel)
            .region(this.region)
            .status(Status.DEFAULT)
            .build();
    }
}
