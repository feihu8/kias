package com.kias.service;

import java.util.List;

import com.kias.model.Userinfo;

public interface UserinfoService {
	
	//查询用户服务
	public List<Userinfo> findUserByParams(Userinfo user);

}
