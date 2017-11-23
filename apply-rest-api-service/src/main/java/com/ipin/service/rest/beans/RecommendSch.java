package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * RecommendSchool.
 * 推荐学校.
 * 
 * @author zhongyongsheng
 *
 */
public class RecommendSch implements Serializable{

	private static final long serialVersionUID = -7546003871589188274L;
	
	private int rank_index;
	private String sch_id;
	private String sch_name;
	private String rank_str;
	private String city;
	private String school_type;

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

	public String getRank_str() {
		return rank_str;
	}

	public void setRank_str(String rank_str) {
		this.rank_str = rank_str;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSchool_type() {
		return school_type;
	}

	public void setSchool_type(String school_type) {
		this.school_type = school_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + rank_index;
		result = prime * result + ((rank_str == null) ? 0 : rank_str.hashCode());
		result = prime * result + ((sch_id == null) ? 0 : sch_id.hashCode());
		result = prime * result + ((sch_name == null) ? 0 : sch_name.hashCode());
		result = prime * result + ((school_type == null) ? 0 : school_type.hashCode());
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
		RecommendSch other = (RecommendSch) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (rank_index != other.rank_index)
			return false;
		if (rank_str == null) {
			if (other.rank_str != null)
				return false;
		} else if (!rank_str.equals(other.rank_str))
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
		if (school_type == null) {
			if (other.school_type != null)
				return false;
		} else if (!school_type.equals(other.school_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RecommendSch [rank_index=" + rank_index + ", sch_id=" + sch_id + ", sch_name=" + sch_name
				+ ", rank_str=" + rank_str + ", city=" + city + ", school_type=" + school_type + "]";
	}

	
}
