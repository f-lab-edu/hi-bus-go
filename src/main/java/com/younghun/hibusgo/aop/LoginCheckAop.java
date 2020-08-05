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
   * @author 조영훈
   * <p>LoginCheck가 적용된 메소드에 접근시 로그인 여부를 체크한다</p>
   * @throws HttpClientErrorException 권한이 없습니다.
   */
  @Before("@annotation(LoginCheck)")
  public void loginCheck() throws HttpClientErrorException {
    boolean islogin = loginService.isLoginAccount();

    if (!islogin) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
  }
}
