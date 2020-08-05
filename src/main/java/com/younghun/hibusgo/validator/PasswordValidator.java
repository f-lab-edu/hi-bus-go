package com.younghun.hibusgo.validator;

import com.younghun.hibusgo.dto.PasswordDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 비밀번호 검증 클래스
 * @author 조영훈
 */
@Component
@Log4j2
public class PasswordValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.isAssignableFrom(PasswordDto.class);
  }

  /**
   * 비밀번호 검증 메소드
   * @author 조영훈
   * 새 패스워드, 새 패스워드 확인이 같은지 검증한다.
   * 입력한 두 패스워드가 다르다면 에러 정보를 넣는다.
   *
   * @param target 검증할 대상
   * @param errors 에러 정보
   */
  @Override
  public void validate(Object target, Errors errors) {
    PasswordDto passwordDto = (PasswordDto) target;
    if (!passwordDto.getNewPassword().equals(passwordDto.getNewPasswordConfirm())) {
      errors.rejectValue("newPassword", "wrong.value", "입력한 새 패스워드가 일치하지 않습니다.");
    }
  }
}
