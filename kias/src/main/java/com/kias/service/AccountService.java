package com.kias.service;

import java.util.List;

import com.kias.model.Account;

public interface AccountService {
	//通过登录名和密码查询
	public Account findAccountByUsername(String aName);
	//通用查询
	public List<Account> findAccountByParams(Account at);
	
}
