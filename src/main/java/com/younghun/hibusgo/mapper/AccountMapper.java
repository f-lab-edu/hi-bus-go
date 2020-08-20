package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    Account findById(Long id);

    void addAccount(Account account);

    boolean existsByUserId(String id);

    Account findByUserI(String userId);

    Account findByUserIdAndPassword(String userId, String password);

    void deleteAccount(Long id);

    void updatePassword(Long accountId, String password);

    void updateAccountInfo(Account account);
}
