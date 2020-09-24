package com.younghun.hibusgo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *  ResponseEntity를 build()를 통해 매번 새로 생성하던 객체 대신에 static 객체를 미리 생성하여 사용
 *  @author 조영훈
 *
 *  객체를 new로 생성을 많이 하면 소수의 사용자가 접근해도 새로운 객체가 자주 생성되고, GC가 자주 발생한다.
 *  GC를 수행하는 동안 많은 메모리 소비와 jvm 수행이 멈추고 요청을 처리하는 동안 대기하는 경우가 발생한다.
 *  이러한 이유로 새로운 객체롤 최대한 적게 생성하도록 지향.
 */
public class ResponseConstants {
  public static final ResponseEntity<Void> RESPONSE_ENTITY_NO_CONTENT = ResponseEntity.noContent().build();
  public static final ResponseEntity<Void> RESPONSE_ENTITY_CREATED = ResponseEntity.status(HttpStatus.CREATED).build();
  public static final ResponseEntity<Void> RESPONSE_ENTITY_OK = ResponseEntity.ok().build();
  public static final ResponseEntity<Void> RESPONSE_NOT_FOUND = ResponseEntity.notFound().build();
  public static final ResponseEntity<Void> RESPONSE_CONFLICT = ResponseEntity.status(HttpStatus.CONFLICT).build();

  public static final ResponseEntity<String> RESPONSE_ACCOUNT_BAD_REQUEST = ResponseEntity.badRequest().body("이미 삭제된 회원이거나, 잘못된 회원입니다.");
  public static final ResponseEntity<String> RESPONSE_REGION_BAD_REQUEST = ResponseEntity.badRequest().body("이미 삭제된 지역이거나, 잘못된 지역입니다.");
  public static final ResponseEntity<String> RESPONSE_TERMINAL_BAD_REQUEST = ResponseEntity.badRequest().body("이미 삭제된 터미널이거나, 잘못된 터미널입니다.");
  public static final ResponseEntity<String> RESPONSE_ROUTE_BAD_REQUEST = ResponseEntity.badRequest().body("이미 삭제된 노선이거나, 잘못된 노선입니다.");
  public static final ResponseEntity<String> RESPONSE_RESERVATION_BAD_REQUEST = ResponseEntity.badRequest().body("이미 삭제된 예약이거나, 잘못된 예약정보입니다.");
  public static final ResponseEntity<String> RESPONSE_SEAT_BAD_REQUEST = ResponseEntity.badRequest().body("이미 삭제된 좌석이거나, 잘못된 좌석입니다.");
  public static final ResponseEntity<String> RESPONSE_CHARGE_BAD_REQUEST = ResponseEntity.badRequest().body("이미 삭제된 요금정보이거나, 잘못된 요금정보입니다.");
}
