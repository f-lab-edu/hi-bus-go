package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Mileage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MileageMapper {

  Mileage findById(Long id);

}
