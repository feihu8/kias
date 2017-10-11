package com.kias.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kias.dao.BaseDictionaryMapper;
import com.kias.model.BaseDictionary;
import com.kias.service.BaseDictionaryService;
import com.mysql.jdbc.StringUtils;
@Service
public class BaseDictionaryServiceImpl implements BaseDictionaryService {
	@Autowired
	private BaseDictionaryMapper dictionaryMapper;
	
	@Override
	public List<BaseDictionary> findDictionaryByConds(BaseDictionary record, String conds,String extra) {
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
			return new ArrayList<BaseDictionary>();
		}
		return dictionaryMapper.selectByDict(map);
	}

	@Override
	public int insertDictionary(BaseDictionary record) {		
		return dictionaryMapper.insertSelective(record);
	}

	@Override
	public int updateDictionary(BaseDictionary record) {		
		return dictionaryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delDictByIds(String id, String ids) {
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
		return dictionaryMapper.delByIds(map);
	}

	@Override
	public int insertBatchDict(List<BaseDictionary> dictList) {
		return dictionaryMapper.addBatchDict(dictList);
	}

	@Override
	public int updateBatchDict(List<BaseDictionary> dictList) {
		return dictionaryMapper.updateBatchDictList(dictList);
	}

}
