package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;

import com.younghun.hibusgo.domain.BusTerminal;
import com.younghun.hibusgo.dto.BusTerminalDto;
import com.younghun.hibusgo.service.BusTerminalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class BusTerminalController {

  private final BusTerminalService busTerminalService;

  /**
   * 터미널 조회 메서드
   * 조회후 값이 없으면 컨텐츠를 리턴하지 않음을 의미하는 204 code return
   * 값이 있으면 ResponseEntity Body에 busTerminal return
   * @param busTerminalDto 조회할 터미널 이름, 지역 이름
   * @return
   */
  @GetMapping("/terminal")
    public ResponseEntity<BusTerminal> getBusTerminal(@RequestBody BusTerminalDto busTerminalDto) {
      String name = busTerminalDto.getName();
      String region = busTerminalDto.getRegion();

      BusTerminal busTerminal = busTerminalService.findByNameAndRegion(name, region);

      if (busTerminal == null) {
        return RESPONSE_ENTITY_NO_CONTENT;
      }

      return ResponseEntity.ok(busTerminal);
    }

}
