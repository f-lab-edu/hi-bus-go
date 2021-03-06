package com.younghun.hibusgo.controller;



import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_CONFLICT;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_CREATED;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_REGION_BAD_REQUEST;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Region;
import com.younghun.hibusgo.dto.RegionDto;
import com.younghun.hibusgo.service.RegionService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class  RegionController {

  private final RegionService regionService;

  /**
   * 지역 조회 메서드
   * @param name 조회할 지역 이름
   * @return List<Region>
   */
  @GetMapping("/{name}")
  public ResponseEntity<List<Region>> getRegion(@PathVariable String name) {
    List<Region> regions = regionService.searchByName(name);

    return ResponseEntity.ok().body(regions);
  }

  /**
   * 지역 추가 메소드
   * @param regionDto 추가할 지역 정보(지역 이름)
   * @return ResponseEntity
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PostMapping()
  public ResponseEntity<?> addRegion(@RequestBody @Valid RegionDto regionDto) {
    String name = regionDto.getName();

    boolean isExistsRegion = regionService.existsByName(name);

    if (isExistsRegion) {
      return RESPONSE_CONFLICT;
    }

    Region region = regionDto.toEntity();

    regionService.addRegion(region);

    return RESPONSE_ENTITY_CREATED;
  }

  /**
   * 지역 전체 조회 메서드
   * @return List<Region>
   */
  @GetMapping()
  public ResponseEntity<List<Region>> getTotalRegion() {
    List<Region> totalRegions = regionService.searchTotal();

    return ResponseEntity.ok().body(totalRegions);
  }

  /**
   * 지역 삭제 메소드
   * @param id 삭제할 지역 아이디
   * @return ResponseEntity
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteRegion(@PathVariable long id) {
    boolean isExistsRegion =  regionService.existsById(id);

    if (!isExistsRegion) {
      return RESPONSE_REGION_BAD_REQUEST;
    }

    regionService.deleteRegion(id);

    return RESPONSE_ENTITY_NO_CONTENT;
  }

}
