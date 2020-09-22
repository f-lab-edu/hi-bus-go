package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Route;
import com.younghun.hibusgo.domain.RouteGrade;
import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RouteDto {

    // 노선 아이디
    private long id;

    // 노선 이름
    @NotBlank
    @Max(255)
    private String name;

    // 거리
    @NotBlank
    private long distance;

    // 노선 등급
    @NotBlank
    private RouteGrade grade;

    // 출발 터미널 아이디
    @NotBlank
    private long departureTerminalId;

    // 도착 터미널 아이디
    @NotBlank
    private long arriveTerminalId;

    // 소요시간
    @NotBlank
    private String timeRequired;

    // 출발시간
    @NotBlank
    private LocalDateTime departureTime;

    // 도착시간
    @NotBlank
    private LocalDateTime arriveTime;

    // 노선 상태 DEFAULT(기본), DELETED(삭제됨)
    private DataStatus status;

    public Route toEntity() {
        return Route.builder()
            .id(this.id)
            .name(this.name)
            .distance(this.distance)
            .grade(this.grade)
            .departureTerminalId(this.departureTerminalId)
            .arriveTerminalId(this.arriveTerminalId)
            .timeRequired(this.timeRequired)
            .departureTime(this.departureTime)
            .arriveTime(this.arriveTime)
            .status(DataStatus.DEFAULT)
            .build();
    }
}
