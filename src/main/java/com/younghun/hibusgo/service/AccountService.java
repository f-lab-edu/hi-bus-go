package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class AccountService {

    private final AccountMapper accountMapper;

    @Transactional(readOnly = true)
    public Account findById(String id) {
        Account account = accountMapper.findById(id);
        return account;
    }

    public void addAccount(Account account) {
        accountMapper.addAccount(account);
    }

    @Transactional(readOnly = true)
    public boolean existsById(String id) {
        boolean isExistAccount = accountMapper.existsById(id);
        return isExistAccount;
    }
}
