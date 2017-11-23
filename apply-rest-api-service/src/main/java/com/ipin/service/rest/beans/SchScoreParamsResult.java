package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchScoreParamsResult.
 * 学校录取分参数结果.
 * 
 * @author zhongyongsheng
 *
 */
public class SchScoreParamsResult implements Serializable{

	private static final long serialVersionUID = -8391779391792586879L;
	
	private List<String> province_ids;
	private List<String> types;

	public List<String> getProvince_ids() {
		return province_ids;
	}

	public void setProvince_ids(List<String> province_ids) {
		this.province_ids = province_ids;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

}
