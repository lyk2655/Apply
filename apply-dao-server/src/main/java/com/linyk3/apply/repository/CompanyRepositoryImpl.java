package com.linyk3.apply.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.linyk3.apply.bean.Company;
import com.linyk3.apply.utils.ApplyUtils;
import com.linyk3.thrift.apply.params.ListCompanyInfoParams;

public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

	@Autowired
	private MongoTemplate applyMongoTemplate;

	
	public List<Company> listCompanyInfo(ListCompanyInfoParams params) {
		Criteria c = null;
		if (params.getCompanyIdList() != null && params.getCompanyIdList().size() > 0) {
			c = Criteria.where("_id").in(ApplyUtils.getObjectIdList(params.getCompanyIdList()));
		}
		if (params.getCompanyNameList() != null && params.getCompanyNameList().size() > 0 ) {
			if (c == null) {
				c = Criteria.where("companyName").in(params.getCompanyNameList());
			} else {
				c.and("companyName").in(params.getCompanyNameList());
			}
		}

		Query query;
		if(c == null) {
			query = new Query();
		} else {
			query = new Query(c);
		}
		
		List<Company> list = applyMongoTemplate.find(query, Company.class);
		return list;
	}
}
