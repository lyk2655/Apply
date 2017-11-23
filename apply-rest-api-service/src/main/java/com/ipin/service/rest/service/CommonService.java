package com.ipin.service.rest.service;

import com.ipin.service.rest.beans.IndustryListResult;
import com.ipin.service.rest.beans.LocationListResult;

/**
 * CommonService
 * 通用接口服务.
 * 
 * @author zhongyongsheng
 *
 */
public interface CommonService {
	
	/**
	 * 获取地区列表结果.
	 * 
	 * @param level 级别
	 * @return
	 */
	public LocationListResult getLocationListResult(String level);
	
	/**
	 * 获取行业列表结果.
	 * 
	 * @return
	 */
	public IndustryListResult getIndustryListResult();

}
