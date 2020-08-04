package com.younghun.hibusgo.domain;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Account {

    public enum Status {
        DEFAULT, DELETED
    }

    private String id;

    private String password;

    private String name;

    private String email;

    private String phoneNumber;

    // 상태 DEFAULT(기본), DELETED(삭제됨)
    private Status status;

    // 회원가입일
    private LocalDateTime createdAt;

    // 최종 수정일
    private LocalDateTime updatedAt;
}
