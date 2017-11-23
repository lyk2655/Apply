package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * IndustryListResult.
 * 行业列表结果.
 * 
 * @author zhongyongsheng
 *
 */
public class IndustryListResult implements Serializable{

	private static final long serialVersionUID = -6691704312320892157L;
	
	private List<Industry> industries;

	public List<Industry> getIndustries() {
		return industries;
	}

	public void setIndustries(List<Industry> industries) {
		this.industries = industries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((industries == null) ? 0 : industries.hashCode());
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
		IndustryListResult other = (IndustryListResult) obj;
		if (industries == null) {
			if (other.industries != null)
				return false;
		} else if (!industries.equals(other.industries))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IndustryListResult [industries=" + industries + "]";
	}

}
