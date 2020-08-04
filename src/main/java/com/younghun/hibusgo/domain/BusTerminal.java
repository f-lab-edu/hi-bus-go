package com.younghun.hibusgo.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BusTerminal {

    public enum Status {
        DEFAULT, DELETED
    }

    private int id;

    private String name;

    private String address;

    private String tel;

    private String region;

    // 버스 미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private Status status;
}
