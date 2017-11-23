package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchMajorScoreParamsResult.
 * 学校专业录取分参数结果.
 * 
 * @author zhongyongsheng
 *
 */
public class SchMajorScoreParamsResult implements Serializable{

	private static final long serialVersionUID = -7343132280301854463L;

	private List<String> province_ids;
	private List<String> types;
	private List<String> years;

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

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((province_ids == null) ? 0 : province_ids.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		result = prime * result + ((years == null) ? 0 : years.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchMajorScoreParamsResult other = (SchMajorScoreParamsResult) obj;
		if (province_ids == null) {
			if (other.province_ids != null)
				return false;
		} else if (!province_ids.equals(other.province_ids))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		if (years == null) {
			if (other.years != null)
				return false;
		} else if (!years.equals(other.years))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchMajorScoreParamsResult [province_ids=" + province_ids + ", types=" + types + ", years=" + years
				+ "]";
	}

}
