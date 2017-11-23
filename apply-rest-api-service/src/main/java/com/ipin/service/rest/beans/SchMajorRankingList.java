package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * SchMajorRankingList.
 * 学校专业排行榜.
 * 
 * @author zhongyongsheng
 *
 */
public class SchMajorRankingList implements Serializable{
	
	private static final long serialVersionUID = 656760786400669460L;
	
	private int rank_index;
	private String major_id;
	private String major_name;
	private String major_category;
	private String main_industry;
	private String main_function;
	private boolean major_has_stats;
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
	public String getMajor_category() {
		return major_category;
	}
	public void setMajor_category(String major_category) {
		this.major_category = major_category;
	}
	public String getMain_industry() {
		return main_industry;
	}
	public void setMain_industry(String main_industry) {
		this.main_industry = main_industry;
	}
	public String getMain_function() {
		return main_function;
	}
	public void setMain_function(String main_function) {
		this.main_function = main_function;
	}
	public boolean isMajor_has_stats() {
		return major_has_stats;
	}
	public void setMajor_has_stats(boolean major_has_stats) {
		this.major_has_stats = major_has_stats;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((main_function == null) ? 0 : main_function.hashCode());
		result = prime * result + ((main_industry == null) ? 0 : main_industry.hashCode());
		result = prime * result + ((major_category == null) ? 0 : major_category.hashCode());
		result = prime * result + (major_has_stats ? 1231 : 1237);
		result = prime * result + ((major_id == null) ? 0 : major_id.hashCode());
		result = prime * result + ((major_name == null) ? 0 : major_name.hashCode());
		result = prime * result + rank_index;
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
		SchMajorRankingList other = (SchMajorRankingList) obj;
		if (main_function == null) {
			if (other.main_function != null)
				return false;
		} else if (!main_function.equals(other.main_function))
			return false;
		if (main_industry == null) {
			if (other.main_industry != null)
				return false;
		} else if (!main_industry.equals(other.main_industry))
			return false;
		if (major_category == null) {
			if (other.major_category != null)
				return false;
		} else if (!major_category.equals(other.major_category))
			return false;
		if (major_has_stats != other.major_has_stats)
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
		if (rank_index != other.rank_index)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SchMajorRankingList [rank_index=" + rank_index + ", major_id=" + major_id + ", major_name=" + major_name
				+ ", major_category=" + major_category + ", main_industry=" + main_industry + ", main_function="
				+ main_function + ", major_has_stats=" + major_has_stats + "]";
	}

}
