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

    // 버스 미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private Status status;
}
