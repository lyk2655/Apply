package com.ipin.service.rest.beans;

/**
 * SalaryGrowthSchMajorRankingList. 薪酬增长学校专业排行榜.
 * 
 * @author zhongyongsheng
 *
 */
public class SalaryGrowthSchMajorRankingList extends SchMajorRankingList {

	private static final long serialVersionUID = 960433795043813543L;

	private double grow_ratio;
	private double grow_ratio_rank;
	private double salary_factor;
	private double country_growth_ratio;
	private double percent_ratio;

	public double getGrow_ratio() {
		return grow_ratio;
	}

	public void setGrow_ratio(double grow_ratio) {
		this.grow_ratio = grow_ratio;
	}

	public double getGrow_ratio_rank() {
		return grow_ratio_rank;
	}

	public void setGrow_ratio_rank(double grow_ratio_rank) {
		this.grow_ratio_rank = grow_ratio_rank;
	}

	public double getSalary_factor() {
		return salary_factor;
	}

	public void setSalary_factor(double salary_factor) {
		this.salary_factor = salary_factor;
	}

	public double getCountry_growth_ratio() {
		return country_growth_ratio;
	}

	public void setCountry_growth_ratio(double country_growth_ratio) {
		this.country_growth_ratio = country_growth_ratio;
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
		temp = Double.doubleToLongBits(country_growth_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(grow_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(grow_ratio_rank);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(percent_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(salary_factor);
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
		SalaryGrowthSchMajorRankingList other = (SalaryGrowthSchMajorRankingList) obj;
		if (Double.doubleToLongBits(country_growth_ratio) != Double.doubleToLongBits(other.country_growth_ratio))
			return false;
		if (Double.doubleToLongBits(grow_ratio) != Double.doubleToLongBits(other.grow_ratio))
			return false;
		if (Double.doubleToLongBits(grow_ratio_rank) != Double.doubleToLongBits(other.grow_ratio_rank))
			return false;
		if (Double.doubleToLongBits(percent_ratio) != Double.doubleToLongBits(other.percent_ratio))
			return false;
		if (Double.doubleToLongBits(salary_factor) != Double.doubleToLongBits(other.salary_factor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalaryGrowthSchMajorRankingList [grow_ratio=" + grow_ratio + ", grow_ratio_rank=" + grow_ratio_rank
				+ ", salary_factor=" + salary_factor + ", country_growth_ratio=" + country_growth_ratio
				+ ", percent_ratio=" + percent_ratio + "]";
	}

}
