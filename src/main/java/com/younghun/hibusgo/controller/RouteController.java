package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_CONFLICT;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_CREATED;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_TERMINAL_BAD_REQUEST;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Route;
import com.younghun.hibusgo.dto.RouteDto;
import com.younghun.hibusgo.service.BusTerminalService;
import com.younghun.hibusgo.service.RouteService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

  private final RouteService routeService;
  private final BusTerminalService busTerminalService;

  /**
   * 노선 조회 메서드
   * @param routeDto 노선 조회 정보(출발 도착 터미널 아이디, 노선 등급, 출발 시간)
   * @return List<Route>
   */
  @GetMapping()
  public ResponseEntity<List<Route>> getRoutes(@RequestBody RouteDto routeDto) {
    Route route = routeDto.toEntity();
    List<Route> routes = routeService.searchByTerminal(route);

    return ResponseEntity.ok().body(routes);
  }

  /**
   * 노선 추가 메소드
   * @param routeDto 노선 정보
   * @return ResponseEntity
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PostMapping()
  public ResponseEntity<?> addRoute(@RequestBody @Valid RouteDto routeDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

    int departureTerminalId = routeDto.getDepartureTerminalId(); //출발 터미널 아이디
    int arriveTerminalId = routeDto.getArriveTerminalId(); //도착 터미널 아이디

    //출발 터미널 존재 여부
    boolean existDepartureTerminal = busTerminalService.existsById(departureTerminalId);

    if (!existDepartureTerminal) {
      return RESPONSE_TERMINAL_BAD_REQUEST;
    }

    //도착 터미널 존재 여부
    boolean existArriveTerminal = busTerminalService.existsById(arriveTerminalId);

    if (!existArriveTerminal) {
      return RESPONSE_TERMINAL_BAD_REQUEST;
    }

    Route route = routeDto.toEntity();
    routeService.addRoute(route);

    return RESPONSE_ENTITY_CREATED;
  }

  /**
   * 노선 수정 메소드
   * @param routeDto 노선 정보
   * @return ResponseEntity
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PatchMapping()
  public ResponseEntity<?> updateRoute(@RequestBody @Valid RouteDto routeDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

    int departureTerminalId = routeDto.getDepartureTerminalId(); //출발 터미널 아이디
    int arriveTerminalId = routeDto.getArriveTerminalId(); //도착 터미널 아이디

    //출발 터미널 존재 여부
    boolean existDepartureTerminal = busTerminalService.existsById(departureTerminalId);

    if (!existDepartureTerminal) {
      return RESPONSE_TERMINAL_BAD_REQUEST;
    }

    //도착 터미널 존재 여부
    boolean existArriveTerminal = busTerminalService.existsById(arriveTerminalId);

    if (!existArriveTerminal) {
      return RESPONSE_TERMINAL_BAD_REQUEST;
    }

    Route route = routeDto.toEntity();
    routeService.updateRoute(route);

    return RESPONSE_ENTITY_NO_CONTENT;
  }

}
