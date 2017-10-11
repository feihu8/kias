package com.kias.service;

import java.util.List;

import com.kias.model.Organization;

public interface OrganizatonService {
	//根据科室代码获取科室信息
	List<Organization> findOrgByCodes(Organization org,String departCodes);
	
}
