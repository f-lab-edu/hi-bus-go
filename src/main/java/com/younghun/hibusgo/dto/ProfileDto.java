package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.Account;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class ProfileDto {

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
        .name(this.name)
        .email(this.email)
        .phoneNumber(this.phoneNumber)
        .build();
  }

}
