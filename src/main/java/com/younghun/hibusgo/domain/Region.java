package com.younghun.hibusgo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Region {

    public enum Status { DEFAULT, DELETED}

    //지역 아이디
    private int id;

    //지역 이름
    private String name;

    // 상태
    private String status;
}
