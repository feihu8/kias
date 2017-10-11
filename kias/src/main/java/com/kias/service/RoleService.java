package com.kias.service;

import java.util.List;
import java.util.Set;

import com.kias.model.Resource;
import com.kias.model.Role;

public interface RoleService {
	//查询角色所属的资源权限
	public Set<String> findResByRoles(String roles);
	/*查询角色和菜单父级ID下所属的资源列表
	 * roles：角色集合：逗号隔开
	 * parentId:资源的父级id 
	 * type : 0:菜单，1：按钮
	 */
	public List<Resource> findListResByRoles(String roles,String parentId,String type);
	
	//查询
	/*
     * record:对象的参数
     * conds:自己封装的查询条件
     * extra:额外的条件，如group by 
	 */
	public List<Role> findRoleByConds(Role record,String conds,String extra);
	/*
	 * 删除角色记录，逻辑删除
	 * id:单独删除一条
	 * ids:删除一条
	 */
	public int delRoleByIds(String id ,String ids);
	/*
	 * 批量更新
	 */
	public int updateBatchRole(List<Role> roleList);
	/*
	 * 批量插入
	 */
	public int insertBatchRole(List<Role> roleList);
}
