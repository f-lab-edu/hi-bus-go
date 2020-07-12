package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    Account findById(String id);

    void addAccount(Account account);

    boolean existsById(String id);

    Account findByIdAndPassword(String id, String password);

    void deleteAccount(String id);

    void updatePassword(String id, String password);

    void updateAccountInfo(Account account);
}
