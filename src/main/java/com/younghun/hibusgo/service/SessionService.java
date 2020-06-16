package com.younghun.hibusgo.service;

import javax.servlet.http.HttpSession;

public interface SessionService {

  void accountLogin(HttpSession session, String accountId);

  void accountLogout(HttpSession session);

  boolean isLoginAccount(HttpSession session);

  String getLoginAccountId(HttpSession session);

}
