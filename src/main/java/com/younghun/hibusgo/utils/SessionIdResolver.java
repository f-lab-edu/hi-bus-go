package com.younghun.hibusgo.utils;

import com.younghun.hibusgo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class SessionIdResolver implements HandlerMethodArgumentResolver {

  private final LoginService loginService;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasMethodAnnotation(SessionId.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

      String loginId = loginService.getLoginAccountId();
      return loginId;
  }
}
