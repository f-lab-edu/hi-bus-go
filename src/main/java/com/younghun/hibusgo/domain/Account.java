package com.younghun.hibusgo.domain;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@ToString
public class Account {

    public enum Status {
        // 기본
        DEFAULT,

        // 삭제됨
        DELETED
    }

    //아이디
    private String id;

    //비밀번호
    private String password;

    //이름
    private String name;

    //이메일
    private String email;

    //휴대폰 번호
    private String phoneNumber;

    // 회원 상태 DEFAULT(기본), DELETED(삭제됨)
    private Status status;

    // 회원가입일
    private LocalDateTime createdAt;

    // 최종 수정일
    private LocalDateTime updatedAt;

}
