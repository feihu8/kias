package com.kias.service;

import java.util.List;

import com.kias.model.Resource;
import com.kias.model.ResourceTree;

public interface ResourceService {
	
	//查询
	/*
     * record:对象的参数
     * conds:自己封装的查询条件
     * extra:额外的条件，如group by 
	 */
	public List<Resource> findResourceByConds(Resource record,String conds,String extra);
	/*
	 * 递归调用资源的数据
	 * parentCode:父级资源代码
	 * roles:角色集合，中间用逗号隔开
	 */
	public List<ResourceTree> findResourceByParentCode(String parentCode,String roles);
	/*
	 * 插入资源对象
	 */
	public int insertResource(Resource record);
	/*
	 * 批量插入
	 */
	public int insertBatchResource(List<Resource> resourceList);
	/*
	 * 更新资源对象,条件是id的对象更新
	 */
	public int updateResource(Resource record);
	/*
	 * 删除资源记录，逻辑删除
	 * id:单独删除一条
	 * ids:删除一条
	 */
	public int delResourceByIds(String id ,String ids);
	/*
	 * 批量更新
	 */
	public int updateBatchResource(List<Resource> resourceList);

}