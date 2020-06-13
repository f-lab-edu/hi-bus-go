package com.younghun.hibusgo.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {

  public static final String ACCOUNT_MEMBER_ID = "ACCOUNT_MEMBER_ID";

  /**
   * 계정 로그인 메소드
   * @param session
   * @param accountId
   * 계정 로그인 id를 세션으로 등록
   */
  public static void accountLogin(HttpSession session, String accountId) {
    session.setAttribute(ACCOUNT_MEMBER_ID, accountId);
  }

  /**
   * 계정 로그아웃 메소드
   * @param session
   * 계정 로그인 id 세션 정보 삭제
   */
  public static void accountLogout(HttpSession session) {
    session.removeAttribute(ACCOUNT_MEMBER_ID);
  }

}
