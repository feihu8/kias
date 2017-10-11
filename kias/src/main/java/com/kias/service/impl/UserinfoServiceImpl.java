package com.kias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kias.dao.UserinfoMapper;
import com.kias.model.Userinfo;
import com.kias.service.UserinfoService;
@Service
public class UserinfoServiceImpl implements UserinfoService {
	@Autowired
	private UserinfoMapper userinfoMapper;
	
	@Override
	public List<Userinfo> findUserByParams(Userinfo user) {		
		return userinfoMapper.selectUserByParams(user);
	}

}
