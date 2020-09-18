package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.CreditCard;
import com.younghun.hibusgo.domain.PaymentMeans;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreditCardMapper {

  PaymentMeans findById (long id);

  void addPaymentMeans(CreditCard creditCard);

  void updatePaymentMeans(CreditCard creditCard);

  void deletePaymentMeans(long id);

}
