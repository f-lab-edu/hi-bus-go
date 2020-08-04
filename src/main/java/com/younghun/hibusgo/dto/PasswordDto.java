package com.younghun.hibusgo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class PasswordDto {

  // 패스워드
  @NotBlank
  @Length(min = 8, max = 16)
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]{8,16}$")
  private final String newPassword;

  @NotBlank
  @Length(min = 8, max = 16)
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]{8,16}$")
  private final String newPasswordConfirm;

}
