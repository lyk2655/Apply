package com.ipin.service.rest.utils;

import java.util.List;

import com.ipin.thrift.common.ProvinceInfo;

/**
 * LocationUtils. 位置帮助工具.
 * 
 * @author zhongyongsheng
 *
 */
public class LocationUtils {

	/**
	 * 获取省份ID
	 * @param provinceInfoList
	 * @param provinceName
	 * @return
	 */
	public static String getProvinceId(List<ProvinceInfo> provinceInfoList, String provinceName) {
		if (provinceInfoList == null) {
			return null;
		}

		for (ProvinceInfo provinceInfo : provinceInfoList) {
			if(provinceInfo.getProvinceName().equals(provinceName)) {
				return provinceInfo.getProvinceId();
			}
		}
		
		return null;
	}
	
	/**
	 * 获取省份名字
	 * @param provinceInfoList
	 * @param provinceId
	 * @return
	 */
	public static String getProvinceName(List<ProvinceInfo> provinceInfoList, String provinceId) {
		if (provinceInfoList == null) {
			return null;
		}

		for (ProvinceInfo provinceInfo : provinceInfoList) {
			if(provinceInfo.getProvinceId().equals(provinceId)) {
				return provinceInfo.getProvinceName();
			}
		}
		
		return null;
	}

}
