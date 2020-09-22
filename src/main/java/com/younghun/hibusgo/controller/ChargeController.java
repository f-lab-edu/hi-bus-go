package com.younghun.hibusgo.controller;

import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_CHARGE_BAD_REQUEST;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_CREATED;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ENTITY_NO_CONTENT;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_NOT_FOUND;
import static com.younghun.hibusgo.utils.ResponseConstants.RESPONSE_ROUTE_BAD_REQUEST;

import com.younghun.hibusgo.aop.LoginCheck;
import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Charge;
import com.younghun.hibusgo.domain.RouteGrade;
import com.younghun.hibusgo.domain.SeatGrade;
import com.younghun.hibusgo.dto.ChargeDto;
import com.younghun.hibusgo.service.ChargeService;
import com.younghun.hibusgo.service.RouteService;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/charges")
@RequiredArgsConstructor // 초기화 되지 않은 final field에 대해 생성자를 생성. final field에 의존성 주입
@Log4j2
public class ChargeController {

  private final RouteService routeService;
  private final ChargeService chargeService;

  /**
   * 요금 조회 메소드
   * @param chargeDto 요금 조회 정보
   * @return Charge
   */
  @GetMapping("/route/{routeId}")
  public ResponseEntity<?> getCharge(@PathVariable long routeId, @RequestBody ChargeDto chargeDto) {
    RouteGrade routeGrade = chargeDto.getRouteGrade();
    SeatGrade seatGrade = chargeDto.getSeatGrade();

    //노선 존재 여부
    boolean existRoute = routeService.existsById(routeId);

    if (!existRoute) {
      return RESPONSE_ROUTE_BAD_REQUEST;
    }

    Optional<Charge> charge = chargeService.findByRouteIdAndGrades(routeId, routeGrade, seatGrade);

    if (!charge.isPresent()) {
      return RESPONSE_NOT_FOUND;
    }

    return ResponseEntity.ok().body(charge.get());
  }

  /**
   * 요금 추가 메소드
   * @param chargeDto 요금 정보(노선 아아디, 좌석 등급, 노선 등급, 가격)
   * @return ResponseEntity<?>
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PostMapping()
  public ResponseEntity<?> addCharge(@RequestBody @Valid ChargeDto chargeDto) {
    long routeId = chargeDto.getRoutId();

    //노선 존재 여부
    boolean existRoute = routeService.existsById(routeId);

    if (!existRoute) {
      return RESPONSE_ROUTE_BAD_REQUEST;
    }

    Charge charge = chargeDto.toEntity();

    chargeService.addCharge(charge);

    return RESPONSE_ENTITY_CREATED;
  }

  /**
   * 요금 수정 메소드
   * @param chargeDto 요금 정보(노선 아아디, 좌석 등급, 노선 등급, 가격)
   * @return ResponseEntity<?>
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PatchMapping()
  public ResponseEntity<?> updateCharge(@RequestBody @Valid ChargeDto chargeDto) {
    long routeId = chargeDto.getRoutId();

    //노선 존재 여부
    boolean existRoute = routeService.existsById(routeId);

    if (!existRoute) {
      return RESPONSE_ROUTE_BAD_REQUEST;
    }

    Charge charge = chargeDto.toEntity();

    chargeService.updateCharge(charge);

    return RESPONSE_ENTITY_NO_CONTENT;
  }

  /**
   * 요금 삭제 메소드
   * @param id 요금 아이디
   * @return ResponseEntity<?>
   */
  @LoginCheck(userLevel = UserLevel.ADMIN)
  @PatchMapping("/{id}")
  public ResponseEntity<?> deleteCharge(@PathVariable long id) {

    //요금 존재 여부
    boolean existCharge = chargeService.existsById(id);

    if (!existCharge) {
      return RESPONSE_CHARGE_BAD_REQUEST;
    }

    chargeService.deleteCharge(id);

    return RESPONSE_ENTITY_NO_CONTENT;
  }

}
