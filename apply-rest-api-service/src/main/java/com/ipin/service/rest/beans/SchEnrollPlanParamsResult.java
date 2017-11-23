package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * EnrollPlanParamsResult.
 * 学校招生计划参数
 * 
 * @author zhongyongsheng
 *
 */
public class SchEnrollPlanParamsResult implements Serializable{

	private static final long serialVersionUID = 7880708416404048466L;
	private List<String> province_names;
	private List<String> types;
	private List<String> years;

	public List<String> getProvince_names() {
		return province_names;
	}

	public void setProvince_names(List<String> province_names) {
		this.province_names = province_names;
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
		result = prime * result + ((province_names == null) ? 0 : province_names.hashCode());
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
		SchEnrollPlanParamsResult other = (SchEnrollPlanParamsResult) obj;
		if (province_names == null) {
			if (other.province_names != null)
				return false;
		} else if (!province_names.equals(other.province_names))
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
		return "EnrollPlanParamsResult [province_names=" + province_names + ", types=" + types + ", years=" + years
				+ "]";
	}

}
