package com.linyk3.apply.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.linyk3.apply.bean.Employ;
import com.linyk3.apply.utils.ApplyUtils;
import com.linyk3.thrift.apply.params.ListEmployInfoParams;

public class EmployRepositoryImpl implements EmployRepositoryCustom {

	@Autowired
	private MongoTemplate applyMongoTemplate;

	public List<Employ> listEmployInfo(ListEmployInfoParams params) {
		Criteria c = null;
		if (params.getEmployIdList() != null && params.getEmployIdList().size() > 0) {
			c = Criteria.where("_id").in(ApplyUtils.getObjectIdList(params.getEmployIdList()));
		}
		if (params.getCompanyIdList() != null && params.getCompanyIdList().size() > 0) {
			if (c == null) {
				c = Criteria.where("companyId").in(ApplyUtils.getObjectIdList(params.getCompanyIdList()));
			} else {
				c = Criteria.where("companyId").in(ApplyUtils.getObjectIdList(params.getCompanyIdList()));
			}
		}
		if (params.getSalary_min() > 0) {
			if (c == null) {
				c = Criteria.where("salaryMin").lte(params.getSalary_min());
			} else {
				c.and("salaryMin").lte(params.getSalary_min());
			}
		}
		if (params.getSalary_max() > 0) {
			if (c == null) {
				c = Criteria.where("salaryMax").gte(params.getSalary_max());
			} else {
				c.and("salaryMax").gte(params.getSalary_max());
			}
		}

		Query query;
		if (c == null) {
			query = new Query();
		} else {
			query = new Query(c);
		}

		List<Employ> list = applyMongoTemplate.find(query, Employ.class);
		return list;
	}

}
