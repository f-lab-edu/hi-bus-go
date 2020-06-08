package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.Account;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

// 유저 생성시 유효성 검사를 위한 class
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    // 아이디
    @NotBlank
    @Length(min = 5, max = 20)
    private String id;

    // 패스워드
    @NotBlank
    @Length(min = 8, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$")
    private String password;

    // 이름
    @NotBlank
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String name;

    // 이메일
    @Email
    private String email;

    // 핸드폰 번호
    @Pattern(regexp = "^(01[1|6|7|8|9|0])-(\\d{3,4})-(\\d{4})$")
    private String phoneNumber;

    public Account toEntity() {
        return Account.builder()
                .id(this.id)
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .build();
    }

}
