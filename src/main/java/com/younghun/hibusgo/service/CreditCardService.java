package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.PaymentMeans;
import com.younghun.hibusgo.mapper.CreditCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardService implements PaymentMeansService {

  private final CreditCardMapper creditCardMapper;

  @Override
  public PaymentMeans findById(long id) {
    return creditCardMapper.findById(id);
  }

  @Override
  public void addPaymentMeans(PaymentMeans paymentMeans) {
    creditCardMapper.addPaymentMeans(paymentMeans);
  }

  @Override
  public void updatePaymentMeans(PaymentMeans paymentMeans) {
    creditCardMapper.updatePaymentMeans(paymentMeans);
  }

  @Override
  public void deletePaymentMeansByPaymentId(long paymentId) {
    creditCardMapper.deletePaymentMeansByPaymentId(paymentId);
  }
}
