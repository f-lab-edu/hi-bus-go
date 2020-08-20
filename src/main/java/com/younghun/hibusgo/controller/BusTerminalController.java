package com.younghun.hibusgo.controller;


import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_NOT_FOUND;

import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.service.BusTerminalService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
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
   * @param region 지역 이름
   * @param name 터미널 이름
   * @return BusTerminal
   */
  @GetMapping("/{region}/{name}")
  public ResponseEntity<?> getBusTerminal(@PathVariable String region, @PathVariable String name) {
    Optional<BusTerminal> busTerminal = busTerminalService.findByNameAndRegion(name, region);

    if (!busTerminal.isPresent()) {
      return RESPONSE_NOT_FOUND;
    }

    return ResponseEntity.ok().body(busTerminal.get());
  }

  /**
   * 터미널 조회 메소드
   * @param region 지역 이름
   * @return List<BusTerminal>
   */
  @GetMapping("/{region}")
  public ResponseEntity<?> getBusTerminals(@PathVariable String region) {
    List<BusTerminal> busTerminals = busTerminalService.searchByRegion(region);

    if (busTerminals == null || busTerminals.isEmpty()) {
      return RESPONSE_ENTITY_NO_CONTENT;
    }

    return ResponseEntity.ok().body(busTerminals);
  }

}
