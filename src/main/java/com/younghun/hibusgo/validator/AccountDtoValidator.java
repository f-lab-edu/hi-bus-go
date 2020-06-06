package com.younghun.hibusgo.validator;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.dto.AccountDto;
import com.younghun.hibusgo.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountDtoValidator implements Validator {

    private final AccountMapper accountMapper;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(AccountDto.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        AccountDto AccountDto = (AccountDto)object;
        Account account = AccountDto.toEntity();

        if (accountMapper.existsById(account.getId())) {
            errors.rejectValue("id", "invalid.id", new Object[]{account.getId()}, "이미 사용중인 아이디입니다.");
        }
    }
}
