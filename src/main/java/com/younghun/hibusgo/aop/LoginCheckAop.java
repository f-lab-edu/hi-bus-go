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
   * 회원 로그인 체크 aop
   *
   * LoginCheck가 적용된 메소드에 접근시 로그인 여부를 체크한다.
   * 로그인이 안되어 있을 경우, 권한이 없음을 의미하는 401 code return.
   *
   * @throws HttpClientErrorException
   */
  @Before("@annotation(LoginCheck)")
  public void loginCheck() throws HttpClientErrorException {
    boolean islogin = loginService.isLoginAccount();

    if (!islogin) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
  }
}
