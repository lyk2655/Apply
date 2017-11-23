package com.ipin.service.rest.tasks;

import java.util.List;

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
import com.ipin.thrift.common.CityInfo;
import com.ipin.thrift.common.CommonService;
import com.ipin.thrift.common.ProvinceInfo;

/**
 * CacheAllProvinceTask.
 * 缓存所有省份.
 * 
 * @author zhongyongsheng
 *
 */

@Component
@Lazy(false)
public class CacheAllLocationTask extends Task {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheAllLocationTask.class);
	
	@Resource
	private CommonService.Iface commonService;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, MajorResult> redisTemplate;

	@Scheduled(cron = "${schedule.cache.allprovince.cron}")
	public void doTask() {
		try {
			logger.debug("Updating all province....");
			// 查询所有省份
			List<ProvinceInfo> provinceInfoList = commonService.getAllProvince(null);
			if(provinceInfoList != null) {
				redisTemplate.opsForHash().put(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_PROVINCE_HASH_KEY, provinceInfoList);
				logger.debug("Cache all province success.");
			}
			
			logger.debug("Updating all city...");
			// 查询所有城市
			List<CityInfo> cityInfoList = commonService.getAllCity(null);
			if (cityInfoList != null) {
				redisTemplate.opsForHash().put(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_CITY_HASH_KEY, cityInfoList);
				logger.debug("Cache all city success.");
			}
			
		} catch (TException e) {
			logger.error("Error occured while caching all province , message code : " + e.getMessage(), e);
		}
		
	}
}
