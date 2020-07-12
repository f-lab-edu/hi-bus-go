package com.younghun.hibusgo.service;


import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


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
        return accountMapper.existsById(id);
    }

    public Account findByIdAndPassword(String accountId, String password) {
         return accountMapper.findByIdAndPassword(accountId, password);
    }

    public void deleteAccount(String id) {
        accountMapper.deleteAccount(id);
    }

    public void updatePassword(String acccountId, String newPassword) {
        accountMapper.updatePassword(acccountId, newPassword);
    }

    public void updateAccountInfo(Account account) {
        accountMapper.updateAccountInfo(account);
    }
}
