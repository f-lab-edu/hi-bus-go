package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Mileage;
import com.younghun.hibusgo.domain.Payment;
import com.younghun.hibusgo.domain.PaymentMeansType;
import com.younghun.hibusgo.domain.PaymentStatus;
import com.younghun.hibusgo.domain.RouteGrade;
import com.younghun.hibusgo.domain.SeatGrade;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PaymentDto {

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

    // 결제 수단
    @NotBlank
    private PaymentMeansType means;

    // 카드 번호
    private String cardNumber;

    // 카드 회사
    private String cardName;

    // 입금 계좌 번호
    private String accountNumber;

    // 입금 은행
    private String accountName;

    // 노선 등급
    @NotBlank
    private RouteGrade routeGrade;

    // 좌석 등급
    @NotBlank
    private SeatGrade seatGrade;

    // 좌석 번호
    @NotBlank
    private long seatNumber;

    // 결제 금액
    @NotBlank
    private long charge;

    // 결제 상태(대기, 완료, 취소, 삭제)
    private PaymentStatus status;

    public Payment toEntity() {
        return Payment.builder()
            .id(this.id)
            .accountId(this.accountId)
            .charge(charge)
            .means(this.means)
            .status(this.status)
            .build();
    }

    public Mileage toEntityOfMileage(long mileage) {
        return Mileage.builder()
            .accountId(this.accountId)
            .mileage(mileage)
            .status(DataStatus.DEFAULT)
            .build();
    }

}
