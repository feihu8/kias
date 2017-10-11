package com.kias.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kias.dao.AccountMapper;
import com.kias.model.Account;
import com.kias.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountMapper accountMapper;
	@Override
	public List<Account> findAccountByParams(Account at) {		
		return accountMapper.selectByParams(at);
	}
	@Override
	public Account findAccountByUsername(String aName) {
		Account at = new Account();
		at.setAccountname(aName);
		List<Account> atList = accountMapper.selectByParams(at);
		at=null;
		if(atList.size()>0){
			at=atList.get(0);
		}
		return at;
	}

}
