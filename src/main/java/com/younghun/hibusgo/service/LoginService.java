package com.younghun.hibusgo.service;


import java.util.Optional;

public interface LoginService {

  void accountLogin(Long accountId);

  void accountLogout();

  boolean isLoginAccount();

  Optional<Long> getLoginAccountId();

}
