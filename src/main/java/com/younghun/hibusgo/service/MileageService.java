package com.younghun.hibusgo.service;


import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.domain.Mileage;
import com.younghun.hibusgo.mapper.MileageMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MileageService {

  private final MileageMapper mileageMapper;

  public Optional<Mileage> findByAccountId(long accountId) {
    return Optional.ofNullable(mileageMapper.findByAccountId(accountId))
        .filter(o -> o.getStatus() == DataStatus.DEFAULT);
  }

  public void updateMileage(Mileage mileage) {
    mileageMapper.updateMileage(mileage);
  }

}
