package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.KakaoPay;
import com.younghun.hibusgo.domain.PaymentMeans;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaPayMapper {

  PaymentMeans findById (long id);

  void addPaymentMeans(KakaoPay kakaoPay);

  void updatePaymentMeans(KakaoPay kakaoPay);

  void deletePaymentMeans(long id);

}
