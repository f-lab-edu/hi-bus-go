package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.Payment;
import com.younghun.hibusgo.domain.PaymentMeansType;
import com.younghun.hibusgo.domain.PaymentStatus;
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

    // 회원 아이디
    @NotBlank
    private long accountId;

    // 결제 금액
    private long paymentCharge;

    // 결제 수단
    private PaymentMeansType means;

    // 결제 상태(대기, 완료, 취소, 삭제)
    private PaymentStatus status;

    public Payment toEntity() {
        return Payment.builder()
            .id(this.id)
            .accountId(this.accountId)
            .means(this.means)
            .build();
    }

    public Payment transPayCharge(long payCharge) {
        return Payment.builder()
            .id(this.id)
            .accountId(this.accountId)
            .paymentCharge(payCharge)
            .means(this.means)
            .status(this.status)
            .build();
    }

    public void updateStatus(PaymentMeansType meansType) {
        PaymentStatus status;

       if (PaymentMeansType.DEPOSIT == meansType) {
           status = PaymentStatus.WAITING;
        } else {
           status = PaymentStatus.COMPLETION;
       }

       this.status = status;
    }
}
