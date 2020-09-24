package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Route;
import com.younghun.hibusgo.mapper.RouteMapper;
import com.younghun.hibusgo.utils.CacheKeys;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RouteService {

  private final RouteMapper routeMapper;

  public boolean existsById(long id) {
    return routeMapper.existsById(id);
  }

  /**
   * @Cacheable : 동일 값이 Cache에 있는 경우 Cache에서 데이터를 return합니다.
   * 만약 동일 key 값이 없을 경우 메소드를 실행하고 반환된 결과 값을 Cache에 저장합니다.
   * unless = "#result == null" -> 조회 결과 값이 null이 아닐 경우에만 캐싱
   */
  @Cacheable(value = CacheKeys.ROUTE_SEARCH, key = "#route", unless = "#result == null" ,cacheManager = "redisCacheManager")
  public List<Route> searchRoute(Route route) {
    return routeMapper.searchRoute(route);
  }

  public void addRoute(Route route) {
    routeMapper.addRoute(route);
  }

  public void updateRoute(Route route) {
    routeMapper.updateRoute(route);
  }

  public void deleteRoute(long id) {
    routeMapper.deleteRoute(id);
  }
}
