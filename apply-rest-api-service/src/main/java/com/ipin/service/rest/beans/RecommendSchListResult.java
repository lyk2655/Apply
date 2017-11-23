package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * RecommendSchoolListResult.
 * 推荐学校列表结果.
 * 
 * @author zhongyongsheng
 *
 */
public class RecommendSchListResult implements Serializable{

	private static final long serialVersionUID = -7000351529677277386L;
	
	private List<RecommendSch> schools;

	public List<RecommendSch> getSchools() {
		return schools;
	}

	public void setSchools(List<RecommendSch> schools) {
		this.schools = schools;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schools == null) ? 0 : schools.hashCode());
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
		RecommendSchListResult other = (RecommendSchListResult) obj;
		if (schools == null) {
			if (other.schools != null)
				return false;
		} else if (!schools.equals(other.schools))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RecommendSchoolListResult [schools=" + schools + "]";
	}

}
