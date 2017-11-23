package com.ipin.service.rest.resource.school;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.ipin.service.rest.beans.IndustryListResult;
import com.ipin.service.rest.beans.LocationListResult;
import com.ipin.service.rest.errorhandling.IllegalParameterException;
import com.ipin.service.rest.errorhandling.MissingParameterException;
import com.ipin.service.rest.service.CommonService;
import com.ipin.service.rest.utils.ParameterUtils;

@Component
@Path("/")
public class CommonResource {
	
	@Autowired
	private CommonService restCommonService;
	
	@GET
	@Path("test")
	@Produces({ MediaType.APPLICATION_JSON })
	/**
	 * 获取地区列表
	 * @param level 地区级别 必填 可选值为“province”和“city”
	 * @return
	 */
	public String test() {
		return "123344444444";
	}
	
	@GET
	@Path("location")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "location_list", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取地区列表
	 * @param level 地区级别 必填 可选值为“province”和“city”
	 * @return
	 */
	public LocationListResult getLocationList(@QueryParam("level") String level) {
		// 检测必要参数
		if (StringUtils.isBlank(level)) {
			throw new MissingParameterException("Parameters is not set, please check.");
		}
		
		// 检测级别
		if (!ParameterUtils.isLocationLevel(level)) {
			throw new IllegalParameterException("Parameter level  is error, please check.");
		}
		
		return restCommonService.getLocationListResult(level);
	}
	
	@GET
	@Path("industry")
	@Produces({ MediaType.APPLICATION_JSON })
	@Cacheable(value = "industry_list", cacheManager = "cacheManager", keyGenerator = "keyGenerator")
	/**
	 * 获取行业列表结果.
	 * @return
	 */
	public IndustryListResult getIndustryList() {
		return restCommonService.getIndustryListResult();
	}
	
}
