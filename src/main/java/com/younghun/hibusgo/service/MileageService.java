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

  public Optional<Mileage> findById(Long id) {
    return Optional.ofNullable(mileageMapper.findById(id))
        .filter(o -> o.getStatus() == DataStatus.DEFAULT);
  }

  public void addMileage(Mileage mileage) {
    mileageMapper.addMileage(mileage);
  }
}
