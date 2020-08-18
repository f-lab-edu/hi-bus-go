package com.younghun.hibusgo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BusTerminal {

    public enum Status {
        // 기본
        DEFAULT,

        // 삭제됨
        DELETED
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

    // 버스 터미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private Status status;
}
