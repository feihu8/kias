package com.kias.dao;

import java.util.List;

import com.kias.model.Userinfo;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
    //通用查询，用户姓名模糊查询
    List<Userinfo> selectUserByParams(Userinfo user);
}