package com.younghun.hibusgo.validator;

import com.younghun.hibusgo.dto.PasswordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Log4j2
public class PasswordValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.isAssignableFrom(PasswordDto.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    PasswordDto passwordDto = (PasswordDto) target;
    if (!passwordDto.getNewPassword().equals(passwordDto.getNewPasswordConfirm())) {
      errors.rejectValue("newPassword", "wrong.value", "입력한 새 패스워드가 일치하지 않습니다.");
    }
  }
}
