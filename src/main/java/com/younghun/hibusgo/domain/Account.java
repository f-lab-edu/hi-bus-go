package com.younghun.hibusgo.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class Account {

    public enum Status { DEFAULT, DELETED}

    private String id;

    private String password;

    private String name;

    private String email;

    private String phoneNumber;

    // 상태
    private Status status;

    // 회원가입일
    private LocalDateTime createdAt;

    // 최종 수정일
    private LocalDateTime updatedAt;
}
