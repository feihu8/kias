package com.kias.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kias.dao.OrganizationMapper;
import com.kias.model.Organization;
import com.kias.service.OrganizatonService;
@Service
public class OrganizationServiceImpl implements OrganizatonService {
	@Autowired
	private OrganizationMapper orgMapper;
	
	public OrganizationMapper getOrgMapper() {
		return orgMapper;
	}

	public void setOrgMapper(OrganizationMapper orgMapper) {
		this.orgMapper = orgMapper;
	}

	@Override
	public List<Organization> findOrgByCodes(Organization org, String departCodes) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("org", org);
		map.put("departCode", null!=departCodes?departCodes.split(","):null);
		if(map.isEmpty()){
			return new ArrayList<Organization>();
		}
		return orgMapper.selectByMap(map);
	}

}
