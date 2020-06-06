package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

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

    public Account login(String accountId, String password) {
        Account account = accountMapper.findByIdAndPassword(accountId, password);
        return account;
    }

    public void logout(HttpSession session) {
        session.removeAttribute("account");
    }
}
