package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.PaymentMeans;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoPayMapper {

  PaymentMeans findById (long id);

  void addPaymentMeans(PaymentMeans paymentMeans);

  void updatePaymentMeans(PaymentMeans paymentMeans);

  void deletePaymentMeans(long id);

}
