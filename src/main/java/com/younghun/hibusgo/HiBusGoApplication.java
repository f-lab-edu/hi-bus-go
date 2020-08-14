package com.younghun.hibusgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @EnableCaching : Application에게 Cache를 사용하다는 의미로 사용
 */
@EnableCaching
@SpringBootApplication
public class HiBusGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiBusGoApplication.class, args);
    }

}
