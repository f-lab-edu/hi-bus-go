package com.younghun.hibusgo.controller;


import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.service.BusTerminalService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminals")
@RequiredArgsConstructor
@Log4j2
public class BusTerminalController {

  private final BusTerminalService busTerminalService;

  /**
   * 터미널 조회 메소드
   *
   * 값이 있으면 ResponseEntity Body에 busTerminal return.
   * 조회후 값이 없으면 컨텐츠를 리턴하지 않음을 의미하는 204 code return.
   *
   * @param region 지역 이름
   * @param name 터미널 이름
   * @return ResponseEntity(성공시 204 code, 실패시 204 code)
   */
  /**
   * @Cacheable : 동일 값이 Cache에 있는 경우 Cache에서 데이터를 return합니다.
   * 만약 동일 key 값이 없을 경우 메소드를 실행하고 반환된 결과 값을 Cache에 저장합니다.
   */
  @Cacheable(value = "terminals.name", key = "#name", cacheManager = "redisCacheManager")
  @GetMapping("/{region}/{name}")
  public BusTerminal getBusTerminal(@PathVariable String region, @PathVariable String name) {
    Optional<BusTerminal> busTerminal = busTerminalService.findByNameAndRegion(name, region);
    return busTerminal.get();
  }

  @Cacheable(value = "terminals.region", key = "#region", cacheManager = "redisCacheManager")
  @GetMapping("/{region}")
  public List<BusTerminal> getBusTerminals(@PathVariable String region) {
    List<BusTerminal> busTerminals = busTerminalService.searchByRegion(region);
    return busTerminals;
  }

}
