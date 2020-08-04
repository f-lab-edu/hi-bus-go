package com.younghun.hibusgo.service;


import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.domain.Account.Status;
import com.younghun.hibusgo.mapper.AccountMapper;
import java.util.Optional;
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

    public Optional<Account> findByIdAndPassword(String accountId, String password) {
         return Optional.ofNullable(accountMapper.findByIdAndPassword(accountId, password))
             .filter(o -> o.getStatus() == Status.DEFAULT);
    }

    public void deleteAccount(String id) {
        accountMapper.deleteAccount(id);
    }

    public void updatePassword(String accountId, String newPassword) {
        accountMapper.updatePassword(accountId, newPassword);
    }

    public void updateAccountInfo(Account account) {
        accountMapper.updateAccountInfo(account);
    }
}
