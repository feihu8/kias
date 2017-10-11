package com.kias.dao;

import java.util.List;

import com.kias.model.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    List<Account> selectByParams(Account at);
}