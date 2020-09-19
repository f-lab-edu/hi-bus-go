package com.younghun.hibusgo.domain;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Payment {

    // 아이디(pk)
    private long id;

    // 회원 아이디
    private long accountId;

    // 결제 금액
    private long paymentCharge;

    // 결제 수단
    private PaymentMeansType means;

    // 결제 상태(대기, 완료, 취소, 삭제)
    private PaymentStatus status;

    // 결제일
    private LocalDateTime createdAt;

    // 결제 수정일
    private LocalDateTime updatedAt;


    public Payment transStatus(PaymentStatus status) {
        return Payment.builder()
            .id(this.id)
            .accountId(this.accountId)
            .paymentCharge(this.paymentCharge)
            .means(this.means)
            .status(status)
            .build();
    }

}
