package com.younghun.hibusgo.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {

  public static final String ACCOUNT_MEMBER_ID = "ACCOUNT_MEMBER_ID";

  public static void accountLogin(HttpSession session, String accountId) {
    session.setAttribute(ACCOUNT_MEMBER_ID, accountId);
  }

  public static void accountLogout(HttpSession session) {
    session.removeAttribute(ACCOUNT_MEMBER_ID);
  }

}
