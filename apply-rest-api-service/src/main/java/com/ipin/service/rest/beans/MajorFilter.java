package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * MajorFilter 专业筛选.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorFilter implements Serializable {

	private static final long serialVersionUID = -7261681193017433172L;
	
	private int rank_index;
	private String major_id;
	private String major_name;
	private int diploma_id;
	private String major_catetory;
	private String major_second_category;
	private double salary_status_rank;
	private int salary_factor;
	private String salary_status_str;
	private double female_ratio;
	private double male_ratio;

	public int getRank_index() {
		return rank_index;
	}

	public void setRank_index(int rank_index) {
		this.rank_index = rank_index;
	}

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public int getDiploma_id() {
		return diploma_id;
	}

	public void setDiploma_id(int diploma_id) {
		this.diploma_id = diploma_id;
	}

	public String getMajor_catetory() {
		return major_catetory;
	}

	public void setMajor_catetory(String major_catetory) {
		this.major_catetory = major_catetory;
	}

	public String getMajor_second_category() {
		return major_second_category;
	}

	public void setMajor_second_category(String major_second_category) {
		this.major_second_category = major_second_category;
	}

	public double getSalary_status_rank() {
		return salary_status_rank;
	}

	public void setSalary_status_rank(double salary_status_rank) {
		this.salary_status_rank = salary_status_rank;
	}

	public String getSalary_status_str() {
		return salary_status_str;
	}

	public void setSalary_status_str(String salary_status_str) {
		this.salary_status_str = salary_status_str;
	}

	public int getSalary_factor() {
		return salary_factor;
	}

	public void setSalary_factor(int salary_factor) {
		this.salary_factor = salary_factor;
	}

	public double getFemale_ratio() {
		return female_ratio;
	}

	public void setFemale_ratio(double female_ratio) {
		this.female_ratio = female_ratio;
		this.male_ratio = 1 - female_ratio;
	}

	public double getMale_ratio() {
		return male_ratio;
	}

	public void setMale_ratio(double male_ratio) {
		this.male_ratio = male_ratio;
		this.female_ratio = 1 - male_ratio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + diploma_id;
		long temp;
		temp = Double.doubleToLongBits(female_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((major_catetory == null) ? 0 : major_catetory.hashCode());
		result = prime * result + ((major_id == null) ? 0 : major_id.hashCode());
		result = prime * result + ((major_name == null) ? 0 : major_name.hashCode());
		result = prime * result + ((major_second_category == null) ? 0 : major_second_category.hashCode());
		temp = Double.doubleToLongBits(male_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rank_index;
		result = prime * result + salary_factor;
		temp = Double.doubleToLongBits(salary_status_rank);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((salary_status_str == null) ? 0 : salary_status_str.hashCode());
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
		MajorFilter other = (MajorFilter) obj;
		if (diploma_id != other.diploma_id)
			return false;
		if (Double.doubleToLongBits(female_ratio) != Double.doubleToLongBits(other.female_ratio))
			return false;
		if (major_catetory == null) {
			if (other.major_catetory != null)
				return false;
		} else if (!major_catetory.equals(other.major_catetory))
			return false;
		if (major_id == null) {
			if (other.major_id != null)
				return false;
		} else if (!major_id.equals(other.major_id))
			return false;
		if (major_name == null) {
			if (other.major_name != null)
				return false;
		} else if (!major_name.equals(other.major_name))
			return false;
		if (major_second_category == null) {
			if (other.major_second_category != null)
				return false;
		} else if (!major_second_category.equals(other.major_second_category))
			return false;
		if (Double.doubleToLongBits(male_ratio) != Double.doubleToLongBits(other.male_ratio))
			return false;
		if (rank_index != other.rank_index)
			return false;
		if (salary_factor != other.salary_factor)
			return false;
		if (Double.doubleToLongBits(salary_status_rank) != Double.doubleToLongBits(other.salary_status_rank))
			return false;
		if (salary_status_str == null) {
			if (other.salary_status_str != null)
				return false;
		} else if (!salary_status_str.equals(other.salary_status_str))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MajorFilter [rank_index=" + rank_index + ", major_id=" + major_id + ", major_name=" + major_name
				+ ", diploma_id=" + diploma_id + ", major_catetory=" + major_catetory + ", major_second_category="
				+ major_second_category + ", salary_status_rank=" + salary_status_rank + ", salary_factor="
				+ salary_factor + ", salary_status_str=" + salary_status_str + ", female_ratio=" + female_ratio
				+ ", male_ratio=" + male_ratio + "]";
	}


}
