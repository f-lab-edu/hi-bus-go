package com.younghun.hibusgo.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    public enum Status { DEFAULT, DELETED}

    // 아이디
    private String id;

    // 패스워드
    private String password;

    // 이름
    private String name;

    // 이메일
    private String email;

    // 핸드폰 번호
    private String phoneNumber;

    // 상태
    private Status status;

    // 회원가입일
    private LocalDateTime createdAt;

    // 최종 수정일
    private LocalDateTime updatedAt;
}
