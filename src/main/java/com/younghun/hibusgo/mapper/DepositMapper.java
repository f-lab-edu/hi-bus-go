package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Deposit;
import com.younghun.hibusgo.domain.PaymentMeans;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepositMapper {

  PaymentMeans findById (long id);

  void addPaymentMeans(Deposit deposit);

  void updatePaymentMeans(Deposit deposit);

  void deletePaymentMeans(long id);

}
