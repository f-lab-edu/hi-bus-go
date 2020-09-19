package com.younghun.hibusgo.domain;

public enum PaymentStatus {
  // 결제 대기
  WAITING,

  // 결제 완료
  COMPLETION,

  // 취소
  CANCELED,

  // 삭제
  DELETED
}
