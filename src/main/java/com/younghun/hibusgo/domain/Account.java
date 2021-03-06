package com.younghun.hibusgo.domain;


import com.younghun.hibusgo.aop.LoginCheck.UserLevel;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Account {

    // 아이디(pk)
    private long id;

    // 회원 아이디(이메일)
    private String userId;

    // 비밀번호
    private String password;

    // 이름
    private String name;

    // 휴대폰 번호
    private String phoneNumber;

    // 사용자 레벨 USER(사용자), ADMIN(관리자)
    private UserLevel userLevel;

    // 회원 상태 DEFAULT(기본), DELETED(삭제됨)
    private DataStatus status;

    // 회원가입일
    private LocalDateTime createdAt;

    // 최종 수정일
    private LocalDateTime updatedAt;

    public Account passwordEncodeCopyAccount(String encodePassword) {
        return Account.builder()
            .userId(this.userId)
            .password(encodePassword)
            .name(this.name)
            .phoneNumber(this.phoneNumber)
            .status(this.status)
            .build();
    }

}
