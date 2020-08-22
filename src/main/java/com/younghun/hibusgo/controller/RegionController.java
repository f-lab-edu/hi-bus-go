package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;

import com.younghun.hibusgo.domain.Region;
import com.younghun.hibusgo.service.RegionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class RegionController {

  private final RegionService regionService;

  /**
   * 지 조회 메서드
   * 조회후 값이 없으면 컨텐츠를 리턴하지 않음을 의미하는 204 code return
   * 값이 있으면 ResponseEntity Body에 Region return
   * @param name 조회할 지역 이름
   * @return ResponseEntity
   */
  /**
   * @Cacheable : 동일 값이 Cache에 있는 경우 Cache에서 데이터를 return합니다.
   * 만약 동일 key 값이 없을 경우 메소드를 실행하고 반환된 결과 값을 Cache에 저장합니다.
   */
  @Cacheable(value = "regions", key = "#name", cacheManager = "redisCacheManager")
  @GetMapping("/{name}")
  public ResponseEntity getRegion(@PathVariable String name) {
    List<Region> regions = regionService.searchByName(name);

    if (regions == null || regions.isEmpty()) {
      return RESPONSE_ENTITY_NO_CONTENT;
    }

    return ResponseEntity.ok().body(regions);
  }

}
