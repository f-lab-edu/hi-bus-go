package com.younghun.hibusgo.config;

import com.younghun.hibusgo.utils.SessionIdResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 커스터마이징한 HandlerMethodArgumentResolver를 등록
 * @author 조영훈
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  /**
   * 커스터마이징한 HandlerMethodArgumentResolver
   * @author 조영훈
   */
  SessionIdResolver sessionIdResolver;

  /**
   * sessionIdResolver를 등록
   * @author 조영훈
   * @param resolvers
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(sessionIdResolver);
  }
}
