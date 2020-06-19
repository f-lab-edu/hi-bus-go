package com.younghun.hibusgo.utils;

import com.younghun.hibusgo.service.LoginService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUtil implements LoginService {

  public static final String ACCOUNT_MEMBER_ID = "ACCOUNT_MEMBER_ID";

  public final HttpSession session;

  /**
   * 계정 로그인 메소드
   * @param accountId
   * 계정 로그인 id를 세션으로 등록
   */
  @Override
  public void accountLogin(String accountId) {
    session.setAttribute(ACCOUNT_MEMBER_ID, accountId);
  }

  /**
   * 계정 로그아웃 메소드
   * 계정 로그인 id 세션 정보 삭제
   */
  @Override
  public void accountLogout() {
    session.removeAttribute(ACCOUNT_MEMBER_ID);
  }

  /**
   * 로그인 여부 확인 메소드
   * @return boolean
   */
  @Override
  public boolean isLoginAccount() {
    String accountId = (String) session.getAttribute(ACCOUNT_MEMBER_ID);
    return accountId != null ? true : false;
  }

  /**
   * 로그인된 사용자 정보 가져오는 메소드
   * @return Account
   */
  @Override
  public String getLoginAccountId() {
    String accountId = (String) session.getAttribute(ACCOUNT_MEMBER_ID);
    return accountId != null ? accountId : null;
  }
}
