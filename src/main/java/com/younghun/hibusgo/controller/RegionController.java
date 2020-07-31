package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;

import com.younghun.hibusgo.domain.Region;
import com.younghun.hibusgo.service.RegionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
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
  @GetMapping
  public ResponseEntity getRegion(String name) {
    List<Region> regions = regionService.findByName(name);

    if (regions == null || regions.isEmpty()) {
      return RESPONSE_ENTITY_NO_CONTENT;
    }

    return ResponseEntity.ok().body(regions);
  }

}