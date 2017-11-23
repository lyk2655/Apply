package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * Industry. 行业.
 * 
 * @author zhongyongsheng
 *
 */
public class Industry implements Serializable {

	private static final long serialVersionUID = -4201494155868547833L;

	private String industry_name;
	private String industry_id;

	public String getIndustry_name() {
		return industry_name;
	}

	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((industry_id == null) ? 0 : industry_id.hashCode());
		result = prime * result + ((industry_name == null) ? 0 : industry_name.hashCode());
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
		Industry other = (Industry) obj;
		if (industry_id == null) {
			if (other.industry_id != null)
				return false;
		} else if (!industry_id.equals(other.industry_id))
			return false;
		if (industry_name == null) {
			if (other.industry_name != null)
				return false;
		} else if (!industry_name.equals(other.industry_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Industry [industry_name=" + industry_name + ", industry_id=" + industry_id + "]";
	}

}
