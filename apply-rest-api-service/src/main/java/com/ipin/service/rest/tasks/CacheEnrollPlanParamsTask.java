package com.ipin.service.rest.tasks;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ipin.service.rest.beans.impl.MajorResult;
import com.ipin.service.rest.constants.Constants;
import com.ipin.thrift.edu.EduEnrollService;
import com.ipin.thrift.edu.SchEnrollPlanDistinctAttribute;
import com.ipin.thrift.edu.commons.EDU_DIPLOMA;
import com.ipin.thrift.edu.params.FindSchEnrollPlanDistinctAttributeParams;

@Component
@Lazy(false)
public class CacheEnrollPlanParamsTask extends Task {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheEnrollPlanParamsTask.class);
	
	@Resource
	private EduEnrollService.Iface eduEnrollService;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, SchEnrollPlanDistinctAttribute> redisTemplate;
	
	@Scheduled(cron = "${schedule.cache.enrollplanparams.cron}")
	public void doTask() {
		try {
			logger.debug("Updating enroll plan params....");
			// 本科
			FindSchEnrollPlanDistinctAttributeParams findSchEnrollPlanDistinctAttributeParams = new FindSchEnrollPlanDistinctAttributeParams();
			findSchEnrollPlanDistinctAttributeParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_BK);
			SchEnrollPlanDistinctAttribute schEnrollPlanDistinctAttribute = eduEnrollService.findSchEnrollPlanDistinctAttribute(findSchEnrollPlanDistinctAttributeParams);
			if(schEnrollPlanDistinctAttribute != null) {
				redisTemplate.opsForHash().put(Constants.REDIS_SCH_ENROLL_PLAN_PARAMS_KEY, EDU_DIPLOMA.EDU_DIPLOMA_BK.getValue(), schEnrollPlanDistinctAttribute);
				logger.debug("Cache bk sch enroll plan params success.");
			}
			
			// 专科
			findSchEnrollPlanDistinctAttributeParams = new FindSchEnrollPlanDistinctAttributeParams();
			findSchEnrollPlanDistinctAttributeParams.setDiplomaId(EDU_DIPLOMA.EDU_DIPLOMA_ZK);
			schEnrollPlanDistinctAttribute = eduEnrollService.findSchEnrollPlanDistinctAttribute(findSchEnrollPlanDistinctAttributeParams);
			if(schEnrollPlanDistinctAttribute != null) {
				redisTemplate.opsForHash().put(Constants.REDIS_SCH_ENROLL_PLAN_PARAMS_KEY, EDU_DIPLOMA.EDU_DIPLOMA_ZK.getValue(), schEnrollPlanDistinctAttribute);
				logger.debug("Cache zk sch enroll plan params success.");
			}
		} catch (TException e) {
			logger.error("Error occured while caching enroll plan params , message code : " + e.getMessage(), e);
		}
		
	}

}
