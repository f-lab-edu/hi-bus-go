package com.younghun.hibusgo.config;

import com.younghun.hibusgo.utils.AccountIdResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 커스터마이징한 HandlerMethodArgumentResolver를 등록
 *
 * 스프링에서 기본적으로 제공하는 resolver에 특정 어노테이션 사용시,
 * 파라미터에 세션아이디를 값을 바인딩하는 resolver가 없어서 커스터마이징한
 * resolver를 등록
 *
 * @author 조영훈
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  /**
   * 커스터마이징한 HandlerMethodArgumentResolver
   */
  AccountIdResolver sessionIdResolver;

  /**
   * sessionIdResolver를 등록
   *
   * 커스터마이징한 HandlerMethodArgumentResolver를 사용하기 위해 등록
   * @param resolvers
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(sessionIdResolver);
  }
}
