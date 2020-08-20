package com.younghun.hibusgo.validator;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.LoginDto;
import com.younghun.hibusgo.mapper.AccountMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 로그인 검증 클래스
 * @author 조영훈
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class LoginDtoValidator implements Validator {

  private final AccountMapper accountMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.isAssignableFrom(LoginDto.class);
  }

  /**
   * 로그인 검증 메소드
   *
   * 아이디, 비밀번호가 일치하는 회원이 있는지 확인한다.
   * 입력한 아이디 또는 패스워드가 다르다면 에러 정보를 넣는다.
   *
   * @param target 검증할 대상
   * @param errors 에러 정보
   */
  @Override
  public void validate(Object target, Errors errors) {
    LoginDto loginDto = (LoginDto) target;
    String userId = loginDto.getUserId();
    String encodePassword = passwordEncoder.encode(loginDto.getPassword());

    Optional<Account> existIngAccount = Optional.ofNullable(accountMapper.findByUserI(userId));

    if (!existIngAccount.isPresent()) {
      errors.rejectValue("Account", "wrong.value", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
    }

    if (!existIngAccount.get().getPassword().equals(encodePassword)) {
      errors.rejectValue("Account", "wrong.value", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
    }
  }
}
