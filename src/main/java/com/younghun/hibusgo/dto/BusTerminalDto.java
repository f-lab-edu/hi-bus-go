package com.younghun.hibusgo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusTerminalDto {

    //터미 이름
    private String name;

    //지역 이름
    private String region;
}
