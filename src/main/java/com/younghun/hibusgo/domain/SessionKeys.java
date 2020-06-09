package com.younghun.hibusgo.domain;

public enum SessionKeys {

  ACCOUNT_MEMBER_ID("ACCOUNT_MEMBER_ID");

  final String keysName;

  SessionKeys(String keysName) {
    this.keysName = keysName;
  }
}
