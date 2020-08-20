package com.younghun.hibusgo.controller;


import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_NOT_FOUND;

import com.younghun.hibusgo.domain.Region;
import com.younghun.hibusgo.service.RegionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
   * 지역 조회 메서드
   * @param name 조회할 지역 이름
   * @return List<Region>
   */
  @GetMapping("/{name}")
  public ResponseEntity<?> getRegion(@PathVariable String name) {
    List<Region> regions = regionService.searchByName(name);

    if (regions == null || regions.isEmpty()) {
      return RESPONSE_NOT_FOUND;
    }

    return ResponseEntity.ok().body(regions);
  }

  /**
   * 지역 전체 조회 메서드
   * @return List<Region>
   */
  @GetMapping()
  public ResponseEntity<?> getTotalRegion() {
    List<Region> totalRegions = regionService.searchTotal();

    if (totalRegions == null || totalRegions.isEmpty()) {
      return RESPONSE_NOT_FOUND;
    }

    return ResponseEntity.ok().body(totalRegions);
  }

}
