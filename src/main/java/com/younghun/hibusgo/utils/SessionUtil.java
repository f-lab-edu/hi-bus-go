package com.younghun.hibusgo.utils;

import com.younghun.hibusgo.service.SessionService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil implements SessionService {

  public static final String ACCOUNT_MEMBER_ID = "ACCOUNT_MEMBER_ID";

  /**
   * 계정 로그인 메소드
   * @param session
   * @param accountId
   * 계정 로그인 id를 세션으로 등록
   */
  @Override
  public void accountLogin(HttpSession session, String accountId) {
    session.setAttribute(ACCOUNT_MEMBER_ID, accountId);
  }

  /**
   * 계정 로그아웃 메소드
   * @param session
   * 계정 로그인 id 세션 정보 삭제
   */
  @Override
  public void accountLogout(HttpSession session) {
    session.removeAttribute(ACCOUNT_MEMBER_ID);
  }

  /**
   * 로그인 여부 확인 메소드
   * @param session
   * @return boolean
   */
  @Override
  public boolean isLoginAccount(HttpSession session) {
    String accountId = (String) session.getAttribute(ACCOUNT_MEMBER_ID);
    return accountId != null ? true : false;
  }

  /**
   * 로그인된 사용자 정부 가져오는 메소드
   * @param session
   * @return Account
   */
  @Override
  public String getLoginAccountId(HttpSession session) {
    return  (String) session.getAttribute(ACCOUNT_MEMBER_ID);
  }
}
