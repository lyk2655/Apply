package com.ipin.service.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import com.ipin.service.rest.beans.Industry;
import com.ipin.service.rest.beans.IndustryListResult;
import com.ipin.service.rest.beans.Location;
import com.ipin.service.rest.beans.LocationListResult;
import com.ipin.service.rest.beans.impl.MajorResult;
import com.ipin.service.rest.constants.Constants;
import com.ipin.service.rest.service.CommonService;
import com.ipin.service.rest.utils.LoggerUtils;
import com.ipin.thrift.common.CityInfo;
import com.ipin.thrift.common.IndustryInfo;
import com.ipin.thrift.common.ProvinceInfo;

/**
 * CommonServiceImpl.
 * 通用服务实现
 * 
 * @author zhongyongsheng
 *
 */
public class CommonServiceImpl implements CommonService{
	
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Resource
	private com.ipin.thrift.common.CommonService.Iface commonService;

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, MajorResult> redisTemplate;

	@Override
	public LocationListResult getLocationListResult(String level) {
		try {
			LocationListResult locationListResult = new LocationListResult();
			List<Location> locationList = new ArrayList<Location>();
			if(Constants.PROVINCE_LEVEL.equals(level)) {
				List<ProvinceInfo> provinceInfoList = null;
				// 获取省份信息
				boolean hasKey = redisTemplate.opsForHash().hasKey(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_PROVINCE_HASH_KEY);
				if (hasKey) {
					provinceInfoList = (List<ProvinceInfo>) redisTemplate.opsForHash().get(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_PROVINCE_HASH_KEY);
				} else {
					provinceInfoList = commonService.getAllProvince(null);
					if (provinceInfoList != null) {
						redisTemplate.opsForHash().put(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_PROVINCE_HASH_KEY, provinceInfoList);
					}
				}
				if (provinceInfoList == null) {
					return null;
				}
				
				for (ProvinceInfo provinceInfo : provinceInfoList) {
					Location location = new Location();
					location.setLoc_id(provinceInfo.getProvinceId());
					location.setName(provinceInfo.getProvinceName());
					locationList.add(location);
				}
			} else if (Constants.CITY_LEVEL.equals(level)) {
				// 获取城市信息
				List<CityInfo> cityInfoList = null;
				boolean hasKey = redisTemplate.opsForHash().hasKey(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_CITY_HASH_KEY);
				if (hasKey) {
					cityInfoList = (List<CityInfo>) redisTemplate.opsForHash().get(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_CITY_HASH_KEY);
				} else {
					cityInfoList = commonService.getAllCity(null);
					if (cityInfoList != null) {
						redisTemplate.opsForHash().put(Constants.REDIS_LOCATION_KEY, Constants.REDIS_ALL_CITY_HASH_KEY, cityInfoList);
					}
				}
				
				if(cityInfoList == null) {
					return null;
				}

				for (CityInfo cityInfo : cityInfoList) {
					Location location = new Location();
					location.setLoc_id(cityInfo.getCityId());
					location.setName(cityInfo.getCityName());
					locationList.add(location);
				}
			}
			locationListResult.setLevel(level);
			locationListResult.setItems(locationList);
			return locationListResult;
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		return null;
	}

	@Override
	public IndustryListResult getIndustryListResult() {
		try {
			// 查询行业信息
			List<IndustryInfo> industryInfoList = commonService.getAllIndustry(null);
			if (industryInfoList == null) {
				return null;
			}
			
			IndustryListResult industryListResult = new IndustryListResult();
			List<Industry> industryList = new ArrayList<Industry>();
			for (IndustryInfo industryInfo : industryInfoList) {
				Industry industry = new Industry();
				industry.setIndustry_id(industryInfo.getIndustryId());
				industry.setIndustry_name(industryInfo.getIndustryName());
				industryList.add(industry);
			}
			industryListResult.setIndustries(industryList);
			return industryListResult;
		} catch (TException e) {
			LoggerUtils.logEduTExceptionAndThrowAppException(logger, e);
		}
		
		return null;
	}

}
