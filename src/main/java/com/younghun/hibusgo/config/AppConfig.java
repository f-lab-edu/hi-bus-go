package com.younghun.hibusgo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

  /**
   *  PasswordEncoder를 구현한 BCryptPasswordEncoder를 Bean으로 등록한다.
   *  DelegatingPasswordEncoder 호출시 , bcrypt방식으로 암호
   *  BCryptPasswordEncoder는 단방향 알고리즘으로 암호화할때 매번 동일한 해시 값이
   *  생성되지 않도록 salt를 랜덤으로 생성하여 암호화할 비밀번호와 더한 값으로 해시를 생성한다.
   * @return PasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
