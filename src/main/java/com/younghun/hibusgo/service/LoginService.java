package com.younghun.hibusgo.service;

import java.util.Optional;

public interface LoginService {

  void accountLogin(String accountId);

  void accountLogout();

  boolean isLoginAccount();

  Optional<String> getLoginAccountId();

}
