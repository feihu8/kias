package com.kias.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kias.model.Role;
import com.kias.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/role")
@Controller
public class RoleController {
	@Autowired
	private RoleService roleSer;
	
	@ResponseBody
	@RequestMapping("/operate")
	public Object operateManage(HttpServletRequest request,HttpServletResponse response){
        String start = request.getParameter("page");   
        String limit = request.getParameter("limit");
		String action= request.getParameter("action");
		String id  = request.getParameter("id");
		int rows=0;//操作的记录数
		if("r".equals(action)){//查询操作
			PageHelper.startPage(null==start ? 0:Integer.parseInt(start), null==limit ? 0:Integer.parseInt(limit)); 
			String rolename = request.getParameter("rolename");
			String sqls = (null==rolename || "".equals(rolename)) ? null:"roleName like '%"+rolename+"'%";
			Role rl = new Role();
			rl.setAvailable("1");
			List<Role> roleList = roleSer.findRoleByConds(rl, sqls, null);
			PageInfo<Role> p=new PageInfo<Role>(roleList);
			return p;
		}else if("c".equals(action)){//增加或更新操作
			List<Role> addRoleList= new ArrayList<Role>();//存放增加角色的List
			List<Role> updateRoleList= new ArrayList<Role>();//存放更新list
			String data = request.getParameter("data");
			JSONArray dataArray = JSONArray.fromObject(data);
			for(int i=0;i< dataArray.size();i++){
				Role role = (Role)JSONObject.toBean(dataArray.getJSONObject(i),Role.class);
				if(role.getId()==0){
					addRoleList.add(role);
				}else{
					updateRoleList.add(role);
				}
			}
			if(!addRoleList.isEmpty()){
				rows=roleSer.insertBatchRole(addRoleList);
			}
			if(!updateRoleList.isEmpty()){
				rows+=roleSer.updateBatchRole(updateRoleList);
			}
		}else if("d".equals(action)){//删除操作
			if(id.indexOf(",")>0){
				rows=roleSer.delRoleByIds(null, id);
			}else{
				rows=roleSer.delRoleByIds(id, null);
			}
		}else if("g".equals(action)){//更新角色和资源关联
			String data = request.getParameter("data");
			String rc = request.getParameter("id");
			Role role = new Role();
			role.setId(Integer.parseInt(rc));
			role.setResourceCodes(data);
			List<Role> updateRole = new ArrayList<Role>();
			updateRole.add(role);
			rows=roleSer.updateBatchRole(updateRole);
			
		}
		return new Integer(rows); 
	}
}
