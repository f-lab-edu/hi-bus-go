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

    //아이디
    private int id;

    //이름
    private String name;

    //주소
    private String address;

    //전화번호
    private String tel;

    //지약
    private String region;

    // 상태
    private Status status;
}
