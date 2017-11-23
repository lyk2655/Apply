package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * MajorFilterListResult.
 * 专业筛选列表结构.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorFilterListResult implements Serializable{

	private static final long serialVersionUID = -4414969353103010131L;
	
	private List<MajorFilter> majors;
	private int total_page;
	private int total_count;

	public List<MajorFilter> getMajors() {
		return majors;
	}

	public void setMajors(List<MajorFilter> majors) {
		this.majors = majors;
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
		result = prime * result + ((majors == null) ? 0 : majors.hashCode());
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
		MajorFilterListResult other = (MajorFilterListResult) obj;
		if (majors == null) {
			if (other.majors != null)
				return false;
		} else if (!majors.equals(other.majors))
			return false;
		if (total_count != other.total_count)
			return false;
		if (total_page != other.total_page)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MajorFilterListResult [majors=" + majors + ", total_page=" + total_page + ", total_count=" + total_count
				+ "]";
	}

}
