package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.PaymentMeans;

public interface PaymentMeansService {

  PaymentMeans findById (long id);

  void addPaymentMeans(PaymentMeans paymentMeans);

  void updatePaymentMeans(PaymentMeans paymentMeans);

  void deletePaymentMeans (long id);
}
