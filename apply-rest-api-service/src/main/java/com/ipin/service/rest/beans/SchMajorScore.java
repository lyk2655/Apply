package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * SchMajorScore.
 * 学校专业录取分数.
 * 
 * @author zhongyongsheng
 *
 */
public class SchMajorScore implements Serializable{

	private static final long serialVersionUID = -7401587980012696143L;
	
	private int rank_index;
	private String major_name;
	private String major_id;
	private boolean major_has_stats;
	private String major_category;
	private String major_second_category;
	private int max_score;
	private int avg_score;
	private int avg_diff_score;
	private String luqu_batch;

	public int getRank_index() {
		return rank_index;
	}

	public void setRank_index(int rank_index) {
		this.rank_index = rank_index;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}

	public boolean isMajor_has_stats() {
		return major_has_stats;
	}

	public void setMajor_has_stats(boolean major_has_stats) {
		this.major_has_stats = major_has_stats;
	}

	public String getMajor_category() {
		return major_category;
	}

	public void setMajor_category(String major_category) {
		this.major_category = major_category;
	}

	public String getMajor_second_category() {
		return major_second_category;
	}

	public void setMajor_second_category(String major_second_category) {
		this.major_second_category = major_second_category;
	}

	public int getMax_score() {
		return max_score;
	}

	public void setMax_score(int max_score) {
		this.max_score = max_score;
	}

	public int getAvg_score() {
		return avg_score;
	}

	public void setAvg_score(int avg_score) {
		this.avg_score = avg_score;
	}

	public int getAvg_diff_score() {
		return avg_diff_score;
	}

	public void setAvg_diff_score(int avg_diff_score) {
		this.avg_diff_score = avg_diff_score;
	}

	public String getLuqu_batch() {
		return luqu_batch;
	}

	public void setLuqu_batch(String luqu_batch) {
		this.luqu_batch = luqu_batch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + avg_diff_score;
		result = prime * result + avg_score;
		result = prime * result + ((luqu_batch == null) ? 0 : luqu_batch.hashCode());
		result = prime * result + ((major_category == null) ? 0 : major_category.hashCode());
		result = prime * result + (major_has_stats ? 1231 : 1237);
		result = prime * result + ((major_id == null) ? 0 : major_id.hashCode());
		result = prime * result + ((major_name == null) ? 0 : major_name.hashCode());
		result = prime * result + ((major_second_category == null) ? 0 : major_second_category.hashCode());
		result = prime * result + max_score;
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
		SchMajorScore other = (SchMajorScore) obj;
		if (avg_diff_score != other.avg_diff_score)
			return false;
		if (avg_score != other.avg_score)
			return false;
		if (luqu_batch == null) {
			if (other.luqu_batch != null)
				return false;
		} else if (!luqu_batch.equals(other.luqu_batch))
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
		if (major_second_category == null) {
			if (other.major_second_category != null)
				return false;
		} else if (!major_second_category.equals(other.major_second_category))
			return false;
		if (max_score != other.max_score)
			return false;
		if (rank_index != other.rank_index)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchMajorScore [rank_index=" + rank_index + ", major_name=" + major_name + ", major_id=" + major_id
				+ ", major_has_stats=" + major_has_stats + ", major_category=" + major_category
				+ ", major_second_category=" + major_second_category + ", max_score=" + max_score + ", avg_score="
				+ avg_score + ", avg_diff_score=" + avg_diff_score + ", luqu_batch=" + luqu_batch + "]";
	}
	
}
