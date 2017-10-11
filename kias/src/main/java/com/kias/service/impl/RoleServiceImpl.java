package com.kias.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kias.dao.ResourceMapper;
import com.kias.dao.RoleMapper;
import com.kias.model.Resource;
import com.kias.model.Role;
import com.kias.service.RoleService;
import com.mysql.jdbc.StringUtils;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ResourceMapper resMapper;
	
	
	@Override
	public Set<String> findResByRoles(String roles) {
		Set<String> resSet = new HashSet<String>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("available", '1');
		params.put("roleCodes", roles.split(","));
		List<Role> roleList = roleMapper.selectByRoles(params);
		StringBuffer sbf= new StringBuffer();
		for(int i=0;i<roleList.size();i++){
			sbf.append(roleList.get(i).getResourceCodes()).append(",");
		}
		if(sbf.length()>0){
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("available", '1');
			res.put("resCodes", new ArrayList<String>(new HashSet<String>(Arrays.asList(sbf.toString().split(",")))));
			List<Resource> resList = resMapper.selectByResCodes(res);
			for(int j=0;j<resList.size();j++){
				resSet.add(resList.get(j).getPermission());
			}
		}else{
			return Collections.emptySet();
		}
		return resSet;
	}


	@Override
	public List<Resource> findListResByRoles(String roles,String parentId,String type) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("available", '1');
		params.put("roleCodes", roles.split(","));
		List<Role> roleList = roleMapper.selectByRoles(params);
		StringBuffer sbf= new StringBuffer();
		for(int i=0;i<roleList.size();i++){
			if(null!=roleList.get(i).getResourceCodes() && !"".equals(roleList.get(i).getResourceCodes())){
				sbf.append(roleList.get(i).getResourceCodes());
			}
		}
		if(sbf.length()>0){
			Map<String,Object> res = new HashMap<String,Object>();
			res.put("available", '1');
			res.put("parentCode", parentId);
			res.put("type", type);
			res.put("resCodes", new ArrayList<String>(new HashSet<String>(Arrays.asList(sbf.toString().split(",")))));
			List<Resource> resList = resMapper.selectByResCodes(res);
			return resList;
		}else{
			return Collections.emptyList();
		}
	}


	@Override
	public List<Role> findRoleByConds(Role record, String conds, String extra) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(null!=record){
			map.put("record", record);
		}
		if(!StringUtils.isNullOrEmpty(conds)){
			map.put("conds", conds);
		}
		if(!StringUtils.isNullOrEmpty(extra)){
			map.put("extra", extra);
		}
		if(map.isEmpty()){
			return new ArrayList<Role>();
		}
		return roleMapper.selectRoleByConds(map);
	}


	@Override
	public int delRoleByIds(String id, String ids) {
		Map<String,Object> map =  new HashMap<String,Object>();
		if(!StringUtils.isNullOrEmpty(id)){
			map.put("id", id);
		}
		if(!StringUtils.isNullOrEmpty(ids)){
			map.put("ids", ids.split(","));
		}
		if(map.isEmpty()){
			return 0;
		}
		return roleMapper.delRoleByIds(map);
	}
	@Override
	public int updateBatchRole(List<Role> roleList) {
		return roleMapper.updateBatchRole(roleList);
	}
	@Override
	public int insertBatchRole(List<Role> roleList) {
		return roleMapper.addBatchRole(roleList);
	}

}
