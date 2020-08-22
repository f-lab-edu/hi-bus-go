package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Region;
import com.younghun.hibusgo.mapper.RegionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RegionService {

  private final RegionMapper regionMapper;

  public Region findById(int id) {
    return regionMapper.findById(id);
  }

  public List<Region> searchByName(String name) {
    return regionMapper.searchByName(name);
  }
}
