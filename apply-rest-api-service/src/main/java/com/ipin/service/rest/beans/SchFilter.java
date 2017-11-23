package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchFilter.
 * 学校筛选.
 * 
 * @author zhongyongsheng
 *
 */
public class SchFilter implements Serializable{

	private static final long serialVersionUID = 8558701188038685567L;
	
	private int rank_index;
	private String sch_id;
	private String sch_name;
	private String sch_type;
	private List<String> sch_grade_tag;
	private String location;
	private String degrees;
	private String total_rank_str;
	private int salary_factor;
	private double female_ratio;
	private double male_ratio;

	public int getRank_index() {
		return rank_index;
	}

	public void setRank_index(int rank_index) {
		this.rank_index = rank_index;
	}

	public String getSch_id() {
		return sch_id;
	}

	public void setSch_id(String sch_id) {
		this.sch_id = sch_id;
	}

	public String getSch_name() {
		return sch_name;
	}

	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}

	public String getSch_type() {
		return sch_type;
	}

	public void setSch_type(String sch_type) {
		this.sch_type = sch_type;
	}

	public List<String> getSch_grade_tag() {
		return sch_grade_tag;
	}

	public void setSch_grade_tag(List<String> sch_grade_tag) {
		this.sch_grade_tag = sch_grade_tag;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getTotal_rank_str() {
		return total_rank_str;
	}

	public void setTotal_rank_str(String total_rank_str) {
		this.total_rank_str = total_rank_str;
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
		this.male_ratio = 1 - this.female_ratio;
	}

	public double getMale_ratio() {
		return male_ratio;
	}

	public void setMale_ratio(double male_ratio) {
		this.male_ratio = male_ratio;
		this.female_ratio = 1 - this.male_ratio;
	}
	
	public void clearSexRatio() {
		this.male_ratio = 0;
		this.female_ratio = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degrees == null) ? 0 : degrees.hashCode());
		long temp;
		temp = Double.doubleToLongBits(female_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		temp = Double.doubleToLongBits(male_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rank_index;
		result = prime * result + salary_factor;
		result = prime * result + ((sch_grade_tag == null) ? 0 : sch_grade_tag.hashCode());
		result = prime * result + ((sch_id == null) ? 0 : sch_id.hashCode());
		result = prime * result + ((sch_name == null) ? 0 : sch_name.hashCode());
		result = prime * result + ((sch_type == null) ? 0 : sch_type.hashCode());
		result = prime * result + ((total_rank_str == null) ? 0 : total_rank_str.hashCode());
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
		SchFilter other = (SchFilter) obj;
		if (degrees == null) {
			if (other.degrees != null)
				return false;
		} else if (!degrees.equals(other.degrees))
			return false;
		if (Double.doubleToLongBits(female_ratio) != Double.doubleToLongBits(other.female_ratio))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (Double.doubleToLongBits(male_ratio) != Double.doubleToLongBits(other.male_ratio))
			return false;
		if (rank_index != other.rank_index)
			return false;
		if (salary_factor != other.salary_factor)
			return false;
		if (sch_grade_tag == null) {
			if (other.sch_grade_tag != null)
				return false;
		} else if (!sch_grade_tag.equals(other.sch_grade_tag))
			return false;
		if (sch_id == null) {
			if (other.sch_id != null)
				return false;
		} else if (!sch_id.equals(other.sch_id))
			return false;
		if (sch_name == null) {
			if (other.sch_name != null)
				return false;
		} else if (!sch_name.equals(other.sch_name))
			return false;
		if (sch_type == null) {
			if (other.sch_type != null)
				return false;
		} else if (!sch_type.equals(other.sch_type))
			return false;
		if (total_rank_str == null) {
			if (other.total_rank_str != null)
				return false;
		} else if (!total_rank_str.equals(other.total_rank_str))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchFilter [rank_index=" + rank_index + ", sch_id=" + sch_id + ", sch_name=" + sch_name + ", sch_type="
				+ sch_type + ", sch_grade_tag=" + sch_grade_tag + ", location=" + location + ", degrees=" + degrees
				+ ", total_rank_str=" + total_rank_str + ", salary_factor=" + salary_factor + ", female_ratio="
				+ female_ratio + ", male_ratio=" + male_ratio + "]";
	}
	
}
