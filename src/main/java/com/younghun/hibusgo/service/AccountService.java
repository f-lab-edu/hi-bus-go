package com.younghun.hibusgo.service;


import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.domain.DataStatus;
import com.younghun.hibusgo.mapper.AccountMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class AccountService {

    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public Account findById(long id) {
        return accountMapper.findById(id);
    }

    public void addAccount(Account account) {
        String encodePassword = passwordEncoder.encode(account.getPassword());

        Account newAccount = account.passwordEncodeCopyAccount(encodePassword);
        accountMapper.addAccount(newAccount);
    }

    public boolean existsById(Long id) {
        return accountMapper.existsById(id);
    }

    public boolean existsByUserId(String id) {
        return accountMapper.existsByUserId(id);
    }

    public Optional<Account> findByUserIdAndPassword(String userId, String password) {
        String encodePassword = passwordEncoder.encode(password);

        return Optional.ofNullable(accountMapper.findByUserIdAndPassword(userId, encodePassword))
            .filter(o -> o.getStatus() == DataStatus.DEFAULT);
    }

    public void deleteAccount(long id) {
        accountMapper.deleteAccount(id);
    }

    public void updatePassword(long accountId, String newPassword) {
        String encodePassword = passwordEncoder.encode(newPassword);
        accountMapper.updatePassword(accountId, encodePassword);
    }

    public void updateAccountInfo(Account account) {
        accountMapper.updateAccountInfo(account);
    }
}
