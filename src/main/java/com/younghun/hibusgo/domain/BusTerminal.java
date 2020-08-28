package com.younghun.hibusgo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 직렬화를 하기 위해서는 getter가 있어야 값에 대한 접근이 가능하다.
 * 역직렬화를 하기 위해서는 객체 생성을 위한 기본 생성자가 반드시 존재해야한다.
 * 근데 Builder사용시 기본 생성자를 생성하지 않아, @NoArgsConstructor 추가해야한다.
 * @NoArgsConstructor만 추가시 빌더는 명시적으로 모든 필드를 갖는 생성자를 필요로하기때문에 @AllArgsConstructor를 추가하여야한다.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class BusTerminal {

    //아이디
    private int id;

    //이름
    private String name;

    //주소
    private String address;

    //전화번호
    private String tel;

    //지역 아이디
    private int regionId;

    // 버스 터미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private DataStatus status;
}
