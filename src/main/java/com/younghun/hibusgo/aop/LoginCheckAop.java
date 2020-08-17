package com.younghun.hibusgo.aop;

import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.service.AccountService;
import com.younghun.hibusgo.service.LoginService;
import java.util.Optional;
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
  private final AccountService accountService;

  /**
   * 회원 로그인 체크 aop
   *
   * LoginCheck가 적용된 메소드에 접근시 로그인 여부를 체크한다.
   * 로그인이 안되어 있을 경우, 권한이 없음을 의미하는 401 code return.
   *
   * @throws HttpClientErrorException
   */
  @Before("@annotation(LoginCheck) && @annotation(loginCheck)")
  public void loginCheck(LoginCheck loginCheck) throws HttpClientErrorException {

    if (UserLevel.ANONYMOUS == loginCheck.userLevel()) {
      defaultLoginCheck();
    }

    if (UserLevel.USER == loginCheck.userLevel()) {
      userLoginCheck();
    }

    if (UserLevel.ADMIN == loginCheck.userLevel()) {
      adminLoginCheck();
    }

  }

  public void defaultLoginCheck() {
    boolean isLogin = loginService.isLoginAccount();

    if (!isLogin) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
  }

  public void userLoginCheck() {
    Optional<Long> accountId = loginService.getLoginAccountId();

    if (!accountId.isPresent()) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }

    Account account = accountService.findById(accountId.get());

    if (UserLevel.USER != account.getUserLevel()) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
  }

  public void adminLoginCheck() {
    Optional<Long> accountId = loginService.getLoginAccountId();

    if (!accountId.isPresent()) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }

    Account account = accountService.findById(accountId.get());

    if (UserLevel.ADMIN != account.getUserLevel()) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
  }
}
