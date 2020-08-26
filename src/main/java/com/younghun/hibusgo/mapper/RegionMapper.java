package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Region;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionMapper {

  Region findById(int id);

  boolean existsByName(String name);

  List<Region> searchByName(String name);

  void addRegion(Region region);

  List<Region> searchTotal();

  void addRegion(String name);

  void deleteRegion(int id);

  void updateRegion(Region region);

  boolean existsById(int regionId);
}
