package com.ipin.service.test.service.test.beans;

import java.util.List;

import com.ipin.service.rest.beans.SalarySchRankingList;

public class SalarySchRankingListResult {

	private List<SalarySchRankingList> schools;
	private int total_page;

	public List<SalarySchRankingList> getSchools() {
		return schools;
	}

	public void setSchools(List<SalarySchRankingList> schools) {
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
		SalarySchRankingListResult other = (SalarySchRankingListResult) obj;
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
		return "SalarySchRankingListResult [schools=" + schools + ", total_page=" + total_page + "]";
	}

}
