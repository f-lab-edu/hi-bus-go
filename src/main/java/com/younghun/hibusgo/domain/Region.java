package com.younghun.hibusgo.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Region {

    public enum Status {
        DEFAULT, DELETED
    }

    //지역 아이디
    private int id;

    //지역 이름
    private String name;

    // 지역 상태(기본, 삭제)
    private String status;
}
