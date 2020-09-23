package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.PaymentMeans;
import com.younghun.hibusgo.mapper.DepositMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositService implements PaymentMeansService {

  private final DepositMapper depositMapper;

  @Override
  public PaymentMeans findById(long id) {
    return depositMapper.findById(id);
  }

  @Override
  public void addPaymentMeans(PaymentMeans paymentMeans) {
    depositMapper.addPaymentMeans(paymentMeans);
  }

  @Override
  public void updatePaymentMeans(PaymentMeans paymentMeans) {
    depositMapper.updatePaymentMeans(paymentMeans);
  }

  @Override
  public void deletePaymentMeansByPaymentId(long paymentId) {
    depositMapper.deletePaymentMeansByPaymentId(paymentId);
  }
}
