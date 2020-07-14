package com.younghun.hibusgo.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *  ResponseEntity를 build()를 통해 매번 새로 생성하던 객체 대신에 static 객체를 미리 생성하여 사용
 *  객체를 new로 생성을 많이 하면 소수의 사용자가 접근해도 새로운 객체가 자주 생성되고, GC가 자주 발생한다.
 *  GC를 수행하는 동안 많은 메모리 소비와 jvm 수행이 멈추고 요청을 처리하는 동안 대기하는 경우가 발생한다.
 *  이러한 이유로 새로운 객체롤 최대한 적게 생성하도록 지향.
 */
@Component
@RequiredArgsConstructor
public class ResponseUtil {

  public static final ResponseEntity responseEntity_NO_CONTENT = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  public static final ResponseEntity responseEntity_UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  public static final ResponseEntity responseEntity_CREATED = ResponseEntity.status(HttpStatus.CREATED).build();
  public static final ResponseEntity responseEntity_OK = ResponseEntity.ok().build();

}
