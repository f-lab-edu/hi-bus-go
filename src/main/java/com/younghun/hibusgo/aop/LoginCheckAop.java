package com.younghun.hibusgo.aop;

import com.younghun.hibusgo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAop {

  private final LoginService loginService;

  /**
   *  회원의 로그인 체크
   * @throws Throwable
   */
  @Before("@annotation(LoginCheck)")
  public void loginCheck() throws HttpClientErrorException {
    boolean islogin = loginService.isLoginAccount();

    if (!islogin) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
  }
}
