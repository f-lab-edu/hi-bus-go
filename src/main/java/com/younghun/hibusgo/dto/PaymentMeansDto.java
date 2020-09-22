package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.CreditCard;
import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Deposit;
import com.younghun.hibusgo.domain.KakaoPay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PaymentMeansDto {

    // 아이디
    private long id;

    // 결제 아이디
    private long paymentId;

    // 카드 번호
    private String cardNumber;

    // 카드 회사 이름
    private String cardName;

    // 입금 계좌 번호
    private String accountNumber;

    // 입금 은행
    private String accountName;

    // 상태
    private DataStatus status;

    // 카카오 페이
    public KakaoPay toEntityOfKakaoPay(PaymentDto paymentDto, long paymentId) {
        return KakaoPay.builder()
            .id(this.id)
            .paymentId(paymentId)
            .cardNumber(paymentDto.getCardNumber())
            .cardName(paymentDto.getCardName())
            .status(DataStatus.DEFAULT)
            .build();
    }

    // 무통장 입금
    public Deposit toEntityOfDeposit(PaymentDto paymentDto, long paymentId) {
        return Deposit.builder()
            .id(this.id)
            .paymentId(paymentId)
            .accountNumber(paymentDto.getAccountNumber())
            .accountName(paymentDto.getAccountName())
            .status(DataStatus.DEFAULT)
            .build();
    }

    // 신용카드
    public CreditCard toEntityOfCreditCard(PaymentDto paymentDto, long paymentId) {
        return CreditCard.builder()
            .id(this.id)
            .paymentId(paymentId)
            .cardNumber(paymentDto.getCardNumber())
            .cardName(paymentDto.getCardName())
            .status(DataStatus.DEFAULT)
            .build();
    }

}
