package com.younghun.hibusgo.validator;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 회원 AccountDto 검증 클래스
 * @author 조영훈
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class AccountDtoValidator implements Validator {

    private final AccountMapper accountMapper;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(AccountDto.class);
    }

    /**
     * 회원 AccountDto 검증 메소드
     *
     * 회원 아이디가 이미 존재하는지 확인한다.
     *
     * @param object 검증 대상
     * @param errors 에러 정보
     */
    @Override
    public void validate(Object object, Errors errors) {
        AccountDto AccountDto = (AccountDto)object;
        Account account = AccountDto.toEntity();

        if (accountMapper.existsByUserId(account.getUserId())) {
            log.error("이미 사용중인 아이디입니다.");
            errors.rejectValue("id", "invalid.id", new Object[]{account.getId()}, "이미 사용중인 아이디입니다.");
        }
    }
}
