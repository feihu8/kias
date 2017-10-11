package com.kias.dao;

import java.util.List;
import java.util.Map;

import com.kias.model.Organization;

public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    //以下是自定义查询
    List<Organization> selectByMap(Map<String,Object> map);
}