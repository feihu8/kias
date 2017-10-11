package com.kias.service;

import java.util.List;

import com.kias.model.BaseDictionary;

public interface BaseDictionaryService {
	//查询
	/*
     * record:对象的参数
     * conds:自己封装的查询条件
     * extra:额外的条件，如group by 
	 */
	public List<BaseDictionary> findDictionaryByConds(BaseDictionary record,String conds,String extra);
	/*
	 * 插入字典对象
	 */
	public int insertDictionary(BaseDictionary record);
	/*
	 * 批量插入
	 */
	public int insertBatchDict(List<BaseDictionary> dictList);
	/*
	 * 更新字典对象,条件是id的对象更新
	 */
	public int updateDictionary(BaseDictionary record);
	/*
	 * 删除字典记录，逻辑删除
	 * id:单独删除一条
	 * ids:删除一条，
	 */
	public int delDictByIds(String id ,String ids);
	/*
	 * 批量更新
	 */
	public int updateBatchDict(List<BaseDictionary> dictList);
	
}
