package com.kias.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kias.model.Account;
import com.kias.model.Resource;
import com.kias.model.ResourceTree;
import com.kias.service.ResourceService;
import com.kias.service.RoleService;
import com.kias.utils.Const;

@RequestMapping("/res")
@Controller
public class ResourceController {
	@Autowired
	private RoleService roleSer;
	@Autowired
	private ResourceService resourceSer;
	
	//初始化菜单
	@ResponseBody
	@RequestMapping("/getByroleRes")
	public List<Resource> findResByRoles(HttpServletRequest request,HttpServletResponse response){
		String parentCode = request.getParameter("parentCode");
		Account act = (Account) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
		List<Resource> resLis=roleSer.findListResByRoles(act.getRoleCodes(),parentCode,"0");
		return resLis;
	}
	//对资源库进行增删改查
	@ResponseBody
	@RequestMapping("/operate")
	public Object operateManage(HttpServletRequest request,HttpServletResponse response){
        String start = request.getParameter("page");   
        String limit = request.getParameter("limit");
		String action= request.getParameter("action");
		String id  = request.getParameter("id");
		String ty = request.getParameter("ty");
		int rows=0;//操作的记录数
		if("r".equals(action)){//分页查询操作
			List<Resource> reslist = new ArrayList<Resource>();
			if(null!=ty && !"".equals(ty)){
				Resource record = new Resource();
				//record.setType(ty);
				record.setAvailable("1");
				PageHelper.startPage(null==start ? 0:Integer.parseInt(start), null==limit ? 0:Integer.parseInt(limit)); 
				reslist = resourceSer.findResourceByConds(record, null, null);
				PageInfo<Resource> p=new PageInfo<Resource>(reslist);  
				return p;
			}
		}else if("rn".equals(action)){//不分页查询
			Resource record = new Resource();
			if(null!=ty && !"".equals(ty)){
				record.setType(ty);
			}
			List<Resource> reslist = resourceSer.findResourceByConds(record, null, null);
			return reslist;
			
		}else if("t".equals(action)){ //树形查询不分页
			String roles = request.getParameter("roles");
			if(null==roles || "".equals(roles)){
				Account act = (Account) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
				roles=act.getRoleCodes();
			}
			List<ResourceTree> resTreelist=resourceSer.findResourceByParentCode("0",roles);
			return resTreelist;			
			
		}else if("c".equals(action)){//增加或更新操作
			List<Resource> addResourceList= new ArrayList<Resource>();//存放增加字典的List
			List<Resource> updateResourceList= new ArrayList<Resource>();//存放更新字典的list
			String data = request.getParameter("data");
			JSONArray dataArray = JSONArray.fromObject(data);
			for(int i=0;i< dataArray.size();i++){
				Resource resource = (Resource)JSONObject.toBean(dataArray.getJSONObject(i),Resource.class);
				if(resource.getId()==0){
					addResourceList.add(resource);
				}else{
					updateResourceList.add(resource);
				}
			}
			if(!addResourceList.isEmpty()){
				rows=resourceSer.insertBatchResource(addResourceList);
			}
			if(!updateResourceList.isEmpty()){
				rows+=resourceSer.updateBatchResource(updateResourceList);
			}
		}else if("d".equals(action)){//删除操作
			if(id.indexOf(",")>0){
				rows=resourceSer.delResourceByIds(null, id);
			}else{
				rows=resourceSer.delResourceByIds(id, null);
			}
		}
		return new Integer(rows);
	}
	

}
