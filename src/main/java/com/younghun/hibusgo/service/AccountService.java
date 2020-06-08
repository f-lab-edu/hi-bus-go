package com.younghun.hibusgo.service;

import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountService {

    private final AccountMapper accountMapper;

    public Account findById(String id) {
        return accountMapper.findById(id);
    }

    public void addAccount(Account account) {
        accountMapper.addAccount(account);
    }

    public boolean existsById(String id) {
        boolean isExistAccount = accountMapper.existsById(id);
        return isExistAccount;
    }

    public Account findByIdAndPassword(String accountId, String password) {
         return accountMapper.findByIdAndPassword(accountId, password);
    }

    public void logout(HttpSession session) {
        session.removeAttribute("account");
    }


    public void login(String id, HttpSession httpSession) {
        httpSession.setAttribute("account", id);
    }
}
