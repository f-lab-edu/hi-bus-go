package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    Account findById(String id);

    int addAccount(Account account);

    boolean existsById(String id);

    Account findByIdAndPassword(String id, String password);
}
