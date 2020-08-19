package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Region;
import com.younghun.hibusgo.mapper.RegionMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RegionService {

  private final RegionMapper regionMapper;

  public Region findById(int id) {
    return regionMapper.findById(id);
  }

  @Scheduled(fixedDelay = 300000) //5분마다 캐시에 저장
  @Cacheable(value = "regions.name", key = "#name", cacheManager = "redisCacheManager")
  public Optional<List<Region>> searchByName(String name) {
    return Optional.ofNullable(regionMapper.searchByName(name));
  }
}
