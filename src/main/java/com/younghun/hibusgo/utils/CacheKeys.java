package com.younghun.hibusgo.utils;

/**
 *  redisCacheManager에서 각 캐시 키 별로 유효기간을 설정하기 위해 키를 미리 생성
 *  @author 조영훈
 *
 *  객체를 new로 생성을 많이 하면 소수의 사용자가 접근해도 새로운 객체가 자주 생성되고, GC가 자주 발생한다.
 *  GC를 수행하는 동안 많은 메모리 소비와 jvm 수행이 멈추고 요청을 처리하는 동안 대기하는 경우가 발생한다.
 *  이러한 이유로 새로운 객체롤 최대한 적게 생성하도록 지향.
 */
public class CacheKeys {
  public static final String TERMINALS_NAME = "terminals.name";
  public static final String TERMINALS_REGION = "terminals.region";
  public static final String TERMINALS_TOTAL = "terminals.total";
  public static final String REGIONS_NAME = "regions.name";
  public static final String REGIONS_TOTAL = "regions.total";
  public static final String ROUTE_SEARCH = "route.search";
  public static final String RESERVATION_ACCOUNT = "reservation.account";
}
