package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.CreditCard;
import com.younghun.hibusgo.domain.Deposit;
import com.younghun.hibusgo.domain.KakaoPay;
import com.younghun.hibusgo.domain.PaymentMeans;
import com.younghun.hibusgo.domain.PaymentMeansType;
import com.younghun.hibusgo.dto.PaymentMeansDto;
import com.younghun.hibusgo.mapper.CreditCardMapper;
import com.younghun.hibusgo.mapper.DepositMapper;
import com.younghun.hibusgo.mapper.KakaPayMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentMeansService {

  private final KakaPayMapper kakaPayMapper;
  private final DepositMapper depositMapper;
  private final CreditCardMapper creditCardMapper;

  PaymentMeans findById (PaymentMeansType meansType, long id) {
    PaymentMeans paymentMeans = null;

    if (PaymentMeansType.KAKAO_PAY == meansType) {
      paymentMeans = kakaPayMapper.findById(id);
    }
    else if (PaymentMeansType.DEPOSIT == meansType) {
      paymentMeans = depositMapper.findById(id);
    }
    else if (PaymentMeansType.CREDIT_CARD == meansType) {
      paymentMeans = creditCardMapper.findById(id);
    }

    return paymentMeans;
  }

  void addPaymentMeans (PaymentMeansType meansType, PaymentMeansDto paymentMeansDtoDto) {
    if (PaymentMeansType.KAKAO_PAY == meansType) {
      KakaoPay kakaoPay = paymentMeansDtoDto.toEntityOfKakaoPay();
      kakaPayMapper.addPaymentMeans(kakaoPay);
    }
    else if (PaymentMeansType.DEPOSIT == meansType) {
      Deposit deposit = paymentMeansDtoDto.toEntityOfDeposit();
      depositMapper.addPaymentMeans(deposit);
    }
    else if (PaymentMeansType.CREDIT_CARD == meansType) {
      CreditCard creditCard = paymentMeansDtoDto.toEntityOfCreditCard();
      creditCardMapper.addPaymentMeans(creditCard);
    }
  }

  void updatePaymentMeans(PaymentMeans paymentMeans) {

  }

  void deletePaymentMeans (long id) {

  }

}
