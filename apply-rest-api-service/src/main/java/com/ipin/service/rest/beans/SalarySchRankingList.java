package com.ipin.service.rest.beans;

/**
 * SalarySchRankingList. 薪酬排行榜.
 * 
 * @author zhongyongsheng
 *
 */
public class SalarySchRankingList extends SchRankingList {

	private static final long serialVersionUID = -4307052719883510267L;

	private String salary_factor_rank_str;
	private int salary_factor_rank_index;

	public String getSalary_factor_rank_str() {
		return salary_factor_rank_str;
	}

	public void setSalary_factor_rank_str(String salary_factor_rank_str) {
		this.salary_factor_rank_str = salary_factor_rank_str;
	}

	public int getSalary_factor_rank_index() {
		return salary_factor_rank_index;
	}

	public void setSalary_factor_rank_index(int salary_factor_rank_index) {
		this.salary_factor_rank_index = salary_factor_rank_index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + salary_factor_rank_index;
		result = prime * result + ((salary_factor_rank_str == null) ? 0 : salary_factor_rank_str.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalarySchRankingList other = (SalarySchRankingList) obj;
		if (salary_factor_rank_index != other.salary_factor_rank_index)
			return false;
		if (salary_factor_rank_str == null) {
			if (other.salary_factor_rank_str != null)
				return false;
		} else if (!salary_factor_rank_str.equals(other.salary_factor_rank_str))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalarySchRankingList [salary_factor_rank_str=" + salary_factor_rank_str + ", salary_factor_rank_index="
				+ salary_factor_rank_index + "]";
	}

}
