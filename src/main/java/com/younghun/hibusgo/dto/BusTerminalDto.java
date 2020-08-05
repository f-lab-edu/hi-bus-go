package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.domain.BusTerminal.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BusTerminalDto {

    //터미널 이름
    private String name;

    //지역 이름
    private String region;

    // 버스 미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private Status status;

    public BusTerminal toEntity() {
        return BusTerminal.builder()
            .name(this.name)
            .region(this.region)
            .status(this.status)
            .build();
    }
}
