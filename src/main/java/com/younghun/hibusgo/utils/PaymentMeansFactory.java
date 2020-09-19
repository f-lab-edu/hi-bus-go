package com.younghun.hibusgo.utils;

import static com.younghun.hibusgo.domain.PaymentStatus.COMPLETION;
import static com.younghun.hibusgo.domain.PaymentStatus.WAITING;

import com.younghun.hibusgo.domain.PaymentMeans;
import com.younghun.hibusgo.domain.PaymentMeansType;
import com.younghun.hibusgo.domain.PaymentStatus;
import com.younghun.hibusgo.dto.PaymentDto;
import com.younghun.hibusgo.dto.PaymentMeansDto;
import com.younghun.hibusgo.service.CreditCardService;
import com.younghun.hibusgo.service.DepositService;
import com.younghun.hibusgo.service.KaKaoPayService;
import com.younghun.hibusgo.service.PaymentMeansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMeansFactory {

  private final KaKaoPayService kaKaoPayService;
  private final DepositService depositService;
  private final CreditCardService creditCardService;

  public PaymentMeansService getType(PaymentMeansType paymentType) {
    final PaymentMeansService paymentMeansService;

    switch (paymentType) {
      case KAKAO_PAY:
        paymentMeansService = kaKaoPayService;
        break;
      case DEPOSIT:
        paymentMeansService = depositService;
        break;
      case CREDIT_CARD:
        paymentMeansService = creditCardService;
        break;
      default:
        throw new IllegalArgumentException();
    }

    return paymentMeansService;
  }

  public PaymentMeans getMeans(long paymentId, PaymentMeansType meansType, PaymentDto paymentDto) {
    final PaymentMeans paymentMeans;
    final PaymentMeansDto paymentMeansDto = new PaymentMeansDto();

    switch (meansType) {
      case KAKAO_PAY:
        paymentMeans = paymentMeansDto.toEntityOfKakaoPay(paymentDto, paymentId);
        break;
      case DEPOSIT:
        paymentMeans = paymentMeansDto.toEntityOfDeposit(paymentDto, paymentId);
        break;
      case CREDIT_CARD:
        paymentMeans = paymentMeansDto.toEntityOfCreditCard(paymentDto, paymentId);
        break;
      default:
        throw new IllegalArgumentException();
    }

    return paymentMeans;
  }

  public PaymentStatus getStatus(PaymentMeansType paymentType) {
    final PaymentStatus paymentStatus;

    switch (paymentType) {
      case KAKAO_PAY:
      case CREDIT_CARD:
        paymentStatus = COMPLETION;
        break;
      case DEPOSIT:
        paymentStatus = WAITING;
        break;
      default:
        throw new IllegalArgumentException();
    }

    return paymentStatus;
  }

}
