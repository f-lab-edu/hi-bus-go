package com.younghun.hibusgo.service;


import com.younghun.hibusgo.domain.Account;
import com.younghun.hibusgo.domain.Account.Status;
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

    public Account findById(String id) {
        return accountMapper.findById(id);
    }

    public void addAccount(Account account) {
        String encodePassword = passwordEncoder.encode(account.getPassword());
        String rawPassword = account.getPassword();

        if (passwordEncoder.matches(rawPassword, encodePassword)) {
            Account newAccount = account.passwordEncodedAccount(encodePassword);
            accountMapper.addAccount(newAccount);
        }
    }

    public boolean existsById(String id) {
        return accountMapper.existsById(id);
    }

    public Optional<Account> findByIdAndPassword(String accountId, String password) {
        String encodePassword = passwordEncoder.encode(password);
         return Optional.ofNullable(accountMapper.findByIdAndPassword(accountId, encodePassword))
             .filter(o -> o.getStatus() == Status.DEFAULT);
    }

    public void deleteAccount(String id) {
        accountMapper.deleteAccount(id);
    }

    public void updatePassword(String accountId, String newPassword) {
        String encodePassword = passwordEncoder.encode(newPassword);
        accountMapper.updatePassword(accountId, encodePassword);
    }

    public void updateAccountInfo(Account account) {
        accountMapper.updateAccountInfo(account);
    }
}
