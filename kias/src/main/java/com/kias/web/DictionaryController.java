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
import com.kias.model.BaseDictionary;
import com.kias.service.BaseDictionaryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/dict")
@Controller
public class DictionaryController {
	@Autowired
	private BaseDictionaryService dictSer;
	@ResponseBody
	@RequestMapping("/operate")
	public Object operateManage(HttpServletRequest request,HttpServletResponse response){
        String start = request.getParameter("page");   
        String limit = request.getParameter("limit");
		String action= request.getParameter("action");
		String id  = request.getParameter("id");
		String types = request.getParameter("types");
		String typename = request.getParameter("typeName");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String sort = request.getParameter("sorts");
		String enabled = request.getParameter("enabled");
		String remarks = request.getParameter("remarks");
		List<BaseDictionary> bdt= null;
		int rows=0;//操作的记录数
		if("r".equals(action)){//查询操作
			PageHelper.startPage(null==start ? 0:Integer.parseInt(start), null==limit ? 0:Integer.parseInt(limit)); 
			bdt=new ArrayList<BaseDictionary>();
			String tp =request.getParameter("tp");
			if(null!=tp && "types".equals(tp)){//按照类型分组查询
				bdt = dictSer.findDictionaryByConds(null, null, "GROUP BY TYPES");
			}else{
				BaseDictionary record = new BaseDictionary();
				record.setCode(code);
				record.setEnabled(enabled);
				record.setId(null==id ? null:Integer.parseInt(id));
				record.setName(name);
				record.setRemarks(remarks);
				record.setSort(null ==sort ? null:Integer.parseInt(sort));
				record.setTypename(typename);
				record.setTypes(types);
				bdt = dictSer.findDictionaryByConds(record, null, null);
				PageInfo<BaseDictionary> p=new PageInfo<BaseDictionary>(bdt);  
				return p;
			}
			return bdt;
		}else if("c".equals(action)){//增加或更新操作
			List<BaseDictionary> addDictList= new ArrayList<BaseDictionary>();//存放增加字典的List
			List<BaseDictionary> updateDictList= new ArrayList<BaseDictionary>();//存放更新字典的list
			String data = request.getParameter("data");
			JSONArray dataArray = JSONArray.fromObject(data);
			for(int i=0;i< dataArray.size();i++){
				BaseDictionary dictionary = (BaseDictionary)JSONObject.toBean(dataArray.getJSONObject(i),BaseDictionary.class);
				if(dictionary.getId()==0){
					addDictList.add(dictionary);
				}else{
					updateDictList.add(dictionary);
				}
			}
			if(!addDictList.isEmpty()){
				rows=dictSer.insertBatchDict(addDictList);
			}
			if(!updateDictList.isEmpty()){
				rows+=dictSer.updateBatchDict(updateDictList);
			}
		}else if("d".equals(action)){//删除操作
			if(id.indexOf(",")>0){
				rows=dictSer.delDictByIds(null, id);
			}else{
				rows=dictSer.delDictByIds(id, null);
			}
		}
		return new Integer(rows);
	}
}
