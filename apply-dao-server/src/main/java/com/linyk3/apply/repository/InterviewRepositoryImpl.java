package com.linyk3.apply.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.linyk3.apply.bean.Interview;
import com.linyk3.apply.bean.Teachin;
import com.linyk3.apply.utils.ApplyUtils;
import com.linyk3.thrift.apply.params.ListInterviewInfoParams;

public class InterviewRepositoryImpl implements InterviewRepositoryCustom {

	@Autowired
	private MongoTemplate applyMongoTemplate;
	
	public List<Interview> listInterviewInfo(ListInterviewInfoParams params) {
		Criteria c = null;
		if (params.getInterviewIdList() != null && params.getInterviewIdList().size() > 0) {
			c = Criteria.where("_id").in(ApplyUtils.getObjectIdList(params.getInterviewIdList()));
		}
		if (params.getCompanyIdList() != null && params.getCompanyIdList().size() > 0) {
			if (c == null) {
				c = Criteria.where("companyId").in(ApplyUtils.getObjectIdList(params.getCompanyIdList()));
			} else {
				c = Criteria.where("companyId").in(ApplyUtils.getObjectIdList(params.getCompanyIdList()));
			}
		}
		if(StringUtils.isNotBlank(params.getBeginDate())) {
			if (c == null) {
				c = Criteria.where("beginDate").lte(params.getBeginDate());
			} else {
				c.and("beginDate").lte(params.getBeginDate());
			}
		}

		if(StringUtils.isNotBlank(params.getEndDate())) {
			if (c == null) {
				c = Criteria.where("endDate").gte(params.getEndDate());
			} else {
				c.and("endDate").gte(params.getEndDate());
			}
		}

		if (params.getStatusList() != null && params.getStatusList().size() > 0) {
			if (c == null) {
				c = Criteria.where("status").in(params.getStatusList());
			} else {
				c = Criteria.where("status").in(params.getStatusList());
			}
		}

		Query query;
		if (c == null) {
			query = new Query();
		} else {
			query = new Query(c);
		}

		List<Interview> list = applyMongoTemplate.find(query, Interview.class);
		return list;
	}

}
