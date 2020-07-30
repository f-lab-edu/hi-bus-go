package com.younghun.hibusgo.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class BusTerminal {

    public enum Status { DEFAULT, DELETED}

    private int id;

    private String name;

    private String address;

    private String tel;

    private String region;

    // 상태
    private Status status;
}
