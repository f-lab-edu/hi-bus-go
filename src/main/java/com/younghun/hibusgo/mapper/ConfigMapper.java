package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Config;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper {

  Config findConfig();

  void updateConfig(Config config);
}
