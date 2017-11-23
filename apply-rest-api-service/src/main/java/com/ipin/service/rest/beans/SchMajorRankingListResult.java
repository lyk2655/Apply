package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchMajorRankingListResult.
 * 学校专业排行榜结果.
 * 
 * @author zhongyongsheng
 *
 */
public class SchMajorRankingListResult implements Serializable{

	private static final long serialVersionUID = 5139477285118819359L;
	
	private List<SchMajorRankingList> majors;

	public List<SchMajorRankingList> getMajors() {
		return majors;
	}

	public void setMajors(List<SchMajorRankingList> majors) {
		this.majors = majors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((majors == null) ? 0 : majors.hashCode());
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
		SchMajorRankingListResult other = (SchMajorRankingListResult) obj;
		if (majors == null) {
			if (other.majors != null)
				return false;
		} else if (!majors.equals(other.majors))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchMajorRankingListResult [majors=" + majors + "]";
	}

}
