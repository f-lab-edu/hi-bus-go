package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.PaymentMeans;
import com.younghun.hibusgo.mapper.KakaoPayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KaKaoPayService implements PaymentMeansService {

  private final KakaoPayMapper kakaoPayMapper;

  @Override
  public PaymentMeans findById(long id) {
    return kakaoPayMapper.findById(id);
  }

  @Override
  public void addPaymentMeans(PaymentMeans paymentMeans) {
    kakaoPayMapper.addPaymentMeans(paymentMeans);
  }

  @Override
  public void updatePaymentMeans(PaymentMeans paymentMeans) {
    kakaoPayMapper.updatePaymentMeans(paymentMeans);
  }

  @Override
  public void deletePaymentMeans(long id) {
    kakaoPayMapper.deletePaymentMeans(id);
  }
}
