package com.kias.dao;

import java.util.List;
import java.util.Map;

import com.kias.model.BaseDictionary;

public interface BaseDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseDictionary record);

    int insertSelective(BaseDictionary record);

    BaseDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseDictionary record);

    int updateByPrimaryKey(BaseDictionary record);
    //自定义的通用的查询
    /*
     * record:对象的参数
     * conds:自己封装的查询条件
     * extra:额外的条件，如group by 
     */
    public List<BaseDictionary> selectByDict(Map<String,Object> map);
    /*
     * 逻辑删除
	 * 删除字典记录，逻辑删除
	 * id:单独删除一条
	 * ids:删除一条，
     */
    int delByIds(Map<String,Object> map);
    /*
     * 批量插入数据
     */
    int addBatchDict(List<BaseDictionary> dictList);
    /*
     * 批量更新数据
     */
    int updateBatchDictList(List<BaseDictionary> dictList);
}