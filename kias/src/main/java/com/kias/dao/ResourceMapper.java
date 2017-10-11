package com.kias.dao;

import java.util.List;
import java.util.Map;

import com.kias.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
    //根据资源代码查询资源
    List<Resource> selectByResCodes(Map<String,Object> map);
    /*通用查询
     * record:存放resource对象，
     * conds:存放自用条件，自己可以拼装
     * extra:存放分组之类的条件
     */
    List<Resource> selectResourceByConds(Map<String,Object> map);
    /*
     * 逻辑删除
	 * id:单独删除一条
	 * ids:删除一条，
     */
    int delResourceByIds(Map<String,Object> map);
    /*
     * 批量插入数据
     */
    int addBatchResource(List<Resource> resourceList);
    /*
     * 批量更新数据
     */
    int updateBatchResource(List<Resource> resourceList);
}