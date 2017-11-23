package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchFilterListResult.
 * 学校筛选列表结果.
 * 
 * @author zhongyongsheng
 *
 */
public class SchFilterListResult implements Serializable{

	private static final long serialVersionUID = -5156400749364171729L;
	
	private List<SchFilter> schools;
	private int total_page;
	private int total_count;

	public List<SchFilter> getSchools() {
		return schools;
	}

	public void setSchools(List<SchFilter> schools) {
		this.schools = schools;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schools == null) ? 0 : schools.hashCode());
		result = prime * result + total_count;
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
		SchFilterListResult other = (SchFilterListResult) obj;
		if (schools == null) {
			if (other.schools != null)
				return false;
		} else if (!schools.equals(other.schools))
			return false;
		if (total_count != other.total_count)
			return false;
		if (total_page != other.total_page)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchFilterListResult [schools=" + schools + ", total_page=" + total_page + ", total_count=" + total_count
				+ "]";
	}

}
