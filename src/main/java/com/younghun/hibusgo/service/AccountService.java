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

    public Account findById(Long id) {
        return accountMapper.findById(id);
    }

    public void addAccount(Account account) {
        accountMapper.addAccount(account);
    }

    public boolean existsByUserId(String id) {
        return accountMapper.existsByUserId(id);
    }

    public Optional<Account> findByUserIdAndPassword(String userId, String password) {
         return Optional.ofNullable(accountMapper.findByUserIdAndPassword(userId, password))
             .filter(o -> o.getStatus() == Status.DEFAULT);
    }

    public void deleteAccount(Long id) {
        accountMapper.deleteAccount(id);
    }

    public void updatePassword(Long accountId, String newPassword) {
        accountMapper.updatePassword(accountId, newPassword);
    }

    public void updateAccountInfo(Account account) {
        accountMapper.updateAccountInfo(account);
    }
}
