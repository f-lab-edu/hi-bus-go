package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Region;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionMapper {
  Region findById(int id);

  List<Region> findByName(String name);

  void addRegion(String name);

  void deleteRegion(int id);

  void updateRegion(Region region);
}
