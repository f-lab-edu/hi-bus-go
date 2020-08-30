package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Mileage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MileageMapper {

  Mileage findByAccountId(long accountId);

  boolean existsByAccountId(long id);

  void updateMileage(Mileage mileage);

  void addMileage(Mileage mileage);
}
