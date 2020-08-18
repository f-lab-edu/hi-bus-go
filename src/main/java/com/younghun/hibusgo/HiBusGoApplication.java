package com.younghun.hibusgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @EnableCaching : 서비스 추상화한 CacheManager 인터페이스를 구현한 Bean을 등록한다.
 * 어플리케이션 내에 캐싱 어노테이션에 있는지 스캔후에 발견되면, 프록시가 자동으로 생성되어
 * 메소드 호출을 가로 채고 그에 따라 캐싱 동작을 처리한다.
 */
@EnableCaching
@SpringBootApplication
public class HiBusGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiBusGoApplication.class, args);
    }

}
