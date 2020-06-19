package com.younghun.hibusgo.service;

public interface LoginService {

  void accountLogin(String accountId);

  void accountLogout();

  boolean isLoginAccount();

  String getLoginAccountId();

}
