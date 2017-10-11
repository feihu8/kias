package com.kias.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kias.dao.ResourceMapper;
import com.kias.dao.RoleMapper;
import com.kias.model.Resource;
import com.kias.model.ResourceTree;
import com.kias.model.Role;
import com.kias.service.ResourceService;
import com.mysql.jdbc.StringUtils;
@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Resource> findResourceByConds(Resource record, String conds, String extra) {
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
			return new ArrayList<Resource>();
		}
		return resourceMapper.selectResourceByConds(map);
	}

	@Override
	public int insertResource(Resource record) {
		return resourceMapper.insertSelective(record);
	}

	@Override
	public int insertBatchResource(List<Resource> resourceList) {
		return resourceMapper.addBatchResource(resourceList);
	}

	@Override
	public int updateResource(Resource record) {
		return resourceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delResourceByIds(String id, String ids) {
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
		return resourceMapper.delResourceByIds(map);
	}

	@Override
	public int updateBatchResource(List<Resource> resourceList) {
		return resourceMapper.updateBatchResource(resourceList);
	}

	@Override
	public List<ResourceTree> findResourceByParentCode(String parentCode, String roles) {
		// StringBuffer resourceCodes = new StringBuffer();
		StringBuffer resCodes= new StringBuffer();
		if(null!=roles && !"".equals(roles)){
			Map<String,Object> map =  new HashMap<String,Object>();
			map.put("roleCodes", roles.split(","));
			List<Role> roleList = roleMapper.selectByRoles(map);
			//StringBuffer resCodes= new StringBuffer();
			for(int i=0;i<roleList.size();i++){
				resCodes.append(roleList.get(i).getResourceCodes()).append(",");
			}
//			if(resCodes.length()>0){
//				Map<String,Object> res = new HashMap<String,Object>();
//				res.put("available", '1');
//				res.put("resCodes", new ArrayList<String>(new HashSet<String>(Arrays.asList(resCodes.toString().split(",")))));
//				List<Resource> resList = resourceMapper.selectByResCodes(res);
//				for(int j=0;j<resList.size();j++){
//					resourceCodes.append(resList.get(j).getResourcecode());
//				}
//			}
		}		
		return getChildResList("0",resCodes.toString());
	}
	//递归调用资源
	public List<ResourceTree> getChildResList(String parentCode, String res) {
		Map<String,Object> map =  new HashMap<String,Object>();
		map.put("parentCode", parentCode); 
		List<Resource> list = resourceMapper.selectByResCodes(map);
		List<ResourceTree> treeList =new ArrayList<ResourceTree>();
		//Collections.copy(treeList, list);
		//treeList.add((ResourceTree) list);
		//BeanUtils.copyProperties(list,treeList);
//		for(int i=0;i<list.size();i++){
//			List<ResourceTree> innerList =getChildResList(list.get(i).getResourcecode(),res);
//			BeanUtils.copyProperties(source, target);
//			treeList.get(i).setChildren(innerList);
//			 if(res.contains(treeList.get(i).getResourcecode())){
//				 treeList.get(i).setChecked("true");
//			 }
//			 System.out.println(treeList.get(i));
//		}
		ResourceTree rt=null;
		for(int i=0;i<list.size();i++){
			 List<ResourceTree> innerList =getChildResList(list.get(i).getResourcecode(),res);
			// rt = (ResourceTree) list.get(i);
			 rt = new ResourceTree();
			 BeanUtils.copyProperties(list.get(i), rt);
			 rt.setChildren(innerList);
			 if(res.contains(list.get(i).getResourcecode())){
				 rt.setChecked(true);
			 }else{
				 rt.setChecked(false);
			 }
			 treeList.add(rt);
		}
		return treeList;		
	}

}
