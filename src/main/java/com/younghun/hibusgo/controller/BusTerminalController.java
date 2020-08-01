package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_NOT_FOUND;

import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.domain.BusTerminal.Status;
import com.younghun.hibusgo.service.BusTerminalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminal")
@RequiredArgsConstructor
@Log4j2
public class BusTerminalController {

  private final BusTerminalService busTerminalService;

  /**
   * 터미널 조회 메서드
   * 조회후 값이 없으면 컨텐츠를 리턴하지 않음을 의미하는 204 code return
   * 값이 있으면 ResponseEntity Body에 busTerminal return
   * @param region 지역 이름
   * @param name 터미널 이름
   * @return
   */
  @GetMapping("/{region}/{name}")
  public ResponseEntity<?> getBusTerminal(@PathVariable String region, @PathVariable String name) {
    BusTerminal busTerminal = busTerminalService.findByNameAndRegion(name, region);

    if (busTerminal == null || busTerminal.getStatus().equals(Status.DELETED)) {
      return RESPONSE_NOT_FOUND;
    }

    return ResponseEntity.ok().body(busTerminal);
  }

}
