package com.kias.dao;

import java.util.List;
import java.util.Map;

import com.kias.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    //通用查询
    List<Role> selectByParams(Role record);
    //根据角色代码查询role色
    List<Role> selectByRoles(Map<String,Object> params);
    /*通用查询
     * record:存放role对象，
     * conds:存放自用条件，自己可以拼装
     * extra:存放分组之类的条件
     */
    List<Role> selectRoleByConds(Map<String,Object> map);
    /*
     * 逻辑删除
	 * id:单独删除一条
	 * ids:删除一条，
     */
    int delRoleByIds(Map<String,Object> map);
    /*
     * 批量插入数据
     */
    int addBatchRole(List<Role> roleList);
    /*
     * 批量更新数据
     */
    int updateBatchRole(List<Role> roleList);
    
}