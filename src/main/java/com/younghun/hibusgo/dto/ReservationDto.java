package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Reservation;
import com.younghun.hibusgo.domain.SeatStatus;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReservationDto {

    // 아이디
    private long id;

    // 노선 아이디
    @NotBlank
    private long routeId;

    // 회원 아이디
    @NotBlank
    private long accountId;

    // 결제 아이디
    private long paymentId;

    // 좌석 상태
    private SeatStatus seatStatus;

    // 예매 상태 DEFAULT(기본), DELETED(삭제됨)
    private DataStatus status;

    public Reservation toEntity() {
        return Reservation.builder()
            .id(this.id)
            .routeId(this.routeId)
            .accountId(this.accountId)
            .paymentId(this.paymentId)
            .status(DataStatus.DEFAULT)
            .build();
    }

    public Reservation transIds(long routeId, long accountId, long paymentId) {
        return Reservation.builder()
            .id(this.id)
            .routeId(routeId)
            .accountId(accountId)
            .paymentId(paymentId)
            .status(DataStatus.DEFAULT)
            .build();
    }

}
