package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Region;
import com.younghun.hibusgo.mapper.RegionMapper;
import java.util.List;
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

  public boolean existsByName(String name) {
    return regionMapper.existsByName(name);
  }

  /**
   * @Cacheable : 동일 값이 Cache에 있는 경우 Cache에서 데이터를 return합니다.
   * 만약 동일 key 값이 없을 경우 메소드를 실행하고 반환된 결과 값을 Cache에 저장합니다.
   */
  @Cacheable(value = "regions.name", key = "#name", cacheManager = "redisCacheManager")
  public List<Region> searchByName(String name) {
    return regionMapper.searchByName(name);
  }

  public void addRegion(Region region) {
    regionMapper.addRegion(region);
  }

  @Scheduled(fixedDelay = 300000L) // 5분마다 캐시 갱신
  @Cacheable(value = "regions.total", key = "'total'")
  public List<Region> searchTotal() {
    return regionMapper.searchTotal();
  }
}
