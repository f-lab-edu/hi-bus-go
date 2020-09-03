package com.younghun.hibusgo.domain;


import java.time.LocalDateTime;
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
public class Route {

    //아이디
    private int id;

    //노선 이름
    private String name;

    //거리
    private int distance;

    //잔여석
    private int seatResidual;

    //노선 등급
    private RouteGrade grade;

    //출발 터미널 아이디
    private int departureTerminalId;

    //도착 터미널 아이디
    private int arriveTerminalId;

    //출발시간
    private LocalDateTime departureTime;

    //도착시간
    private LocalDateTime arriveTime;

    //소요시간
    private LocalDateTime timeRequired;

    //추가일
    private LocalDateTime createAt;

    //수정일
    private LocalDateTime updatedAt;

    // 노선 터미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private DataStatus status;
}
