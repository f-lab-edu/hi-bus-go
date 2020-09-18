package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Payment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

  Payment findById(long id);

  List<Payment> findByAccountId(long id);

  long addPayment(Payment payment);
}
