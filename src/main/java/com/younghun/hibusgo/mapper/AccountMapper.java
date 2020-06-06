package com.younghun.hibusgo.mapper;

import com.younghun.hibusgo.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    public Account findById(String id);

    public int addAccount(Account account);

    public boolean existsById(String id);

    public Account findByIdAndPassword(String id, String password);
}
