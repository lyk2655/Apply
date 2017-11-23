package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * SchMajorRankingList. 学校专业排行榜.
 * 
 * @author zhongyongsheng
 *
 */
public class SalarySchMajorRankingList extends SchMajorRankingList {

	private static final long serialVersionUID = 656760786400669460L;

	private double salary_factor;
	private double salary_factor_rank;
	private double grow_ratio;
	private double country_salary_factor;
	private double percent_ratio;
	public double getSalary_factor() {
		return salary_factor;
	}
	public void setSalary_factor(double salary_factor) {
		this.salary_factor = salary_factor;
	}
	public double getSalary_factor_rank() {
		return salary_factor_rank;
	}
	public void setSalary_factor_rank(double salary_factor_rank) {
		this.salary_factor_rank = salary_factor_rank;
	}
	public double getGrow_ratio() {
		return grow_ratio;
	}
	public void setGrow_ratio(double grow_ratio) {
		this.grow_ratio = grow_ratio;
	}
	public double getCountry_salary_factor() {
		return country_salary_factor;
	}
	public void setCountry_salary_factor(double country_salary_factor) {
		this.country_salary_factor = country_salary_factor;
	}
	public double getPercent_ratio() {
		return percent_ratio;
	}
	public void setPercent_ratio(double percent_ratio) {
		this.percent_ratio = percent_ratio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(country_salary_factor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(grow_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(percent_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(salary_factor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(salary_factor_rank);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SalarySchMajorRankingList other = (SalarySchMajorRankingList) obj;
		if (Double.doubleToLongBits(country_salary_factor) != Double.doubleToLongBits(other.country_salary_factor))
			return false;
		if (Double.doubleToLongBits(grow_ratio) != Double.doubleToLongBits(other.grow_ratio))
			return false;
		if (Double.doubleToLongBits(percent_ratio) != Double.doubleToLongBits(other.percent_ratio))
			return false;
		if (Double.doubleToLongBits(salary_factor) != Double.doubleToLongBits(other.salary_factor))
			return false;
		if (Double.doubleToLongBits(salary_factor_rank) != Double.doubleToLongBits(other.salary_factor_rank))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SalarySchMajorRankingList [salary_factor=" + salary_factor + ", salary_factor_rank="
				+ salary_factor_rank + ", grow_ratio=" + grow_ratio + ", country_salary_factor=" + country_salary_factor
				+ ", percent_ratio=" + percent_ratio + "]";
	}


}
