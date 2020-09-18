package com.younghun.hibusgo.domain;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Route {

    // 아이디
    private long id;

    // 좌석 아이디
    private long seatId;

    // 좌석 번호
    private long seatNumber;

    // 노선 이름
    private String name;

    // 거리
    private long distance;

    // 노선 등급
    private RouteGrade grade;

    // 출발 터미널 아이디
    private long departureTerminalId;

    // 도착 터미널 아이디
    private long arriveTerminalId;

    // 소요시간
    private String timeRequired;

    // 출발시간
    private LocalDateTime departureTime;

    // 도착시간
    private LocalDateTime arriveTime;

    // 추가일
    private LocalDateTime createAt;

    // 수정일
    private LocalDateTime updatedAt;

    // 노선 터미널 상태 DEFAULT(기본), DELETED(삭제됨)
    private DataStatus status;
}
