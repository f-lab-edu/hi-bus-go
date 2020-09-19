package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Payment;
import com.younghun.hibusgo.domain.PaymentStatus;
import com.younghun.hibusgo.mapper.PaymentMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentMapper paymentMapper;

  public Optional<Payment> findById(long id) {
    return Optional.ofNullable(paymentMapper.findById(id))
        .filter(o -> o.getStatus() != PaymentStatus.DELETED);
  }

  public List<Payment> findByAccountId(long id) {
    return paymentMapper.findByAccountId(id);
  }

}
