package com.younghun.hibusgo.dto;

import com.younghun.hibusgo.domain.Account;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * request Body에 있는 json data를 mapping하기 위해, 생성자 추가
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProfileDto {

  // 사용자 아이디
  @NotBlank
  @Email
  @Max(255)
  private String userId;

  // 이름
  @NotBlank
  @Length(min = 3, max = 20)
  @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
  private String name;

  // 핸드폰 번호
  @Pattern(regexp = "^(01[1|6|7|8|9|0])-(\\d{3,4})-(\\d{4})$")
  private String phoneNumber;

  public Account toEntity() {
    return Account.builder()
        .userId(this.userId)
        .name(this.name)
        .phoneNumber(this.phoneNumber)
        .build();
  }

}
