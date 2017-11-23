package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchoolRankingListResult. 
 * 学校排行榜结果
 * 
 * @author zhongyongsheng
 *
 */
public class SchRankingListResult implements Serializable {

	private static final long serialVersionUID = -9022544188529732951L;

	private List<SchRankingList> schools;
	private int total_page;

	public List<SchRankingList> getSchools() {
		return schools;
	}

	public void setSchools(List<SchRankingList> schools) {
		this.schools = schools;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schools == null) ? 0 : schools.hashCode());
		result = prime * result + total_page;
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
		SchRankingListResult other = (SchRankingListResult) obj;
		if (schools == null) {
			if (other.schools != null)
				return false;
		} else if (!schools.equals(other.schools))
			return false;
		if (total_page != other.total_page)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchRankingListResult [schools=" + schools + ", total_page=" + total_page + "]";
	}

}
