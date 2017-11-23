package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * SchScore. 
 * 学校录取分数.
 * 
 * @author zhongyongsheng
 *
 */
public class SchScore implements Serializable {

	private static final long serialVersionUID = 5849467054343046074L;

	private int rank_index;
	private int year;
	private int max_score;
	private int avg_score;
	private int avg_diff_score;
	private int min_score;
	private int luqu_count;
	private String luqu_batch;

	public SchScore() {
		super();
	}

	public SchScore(SchScore schScore) {
		super();
		this.rank_index = schScore.getRank_index();
		this.year = schScore.getYear();
		this.max_score = schScore.getMax_score();
		this.avg_score = schScore.getAvg_score();
		this.avg_diff_score = schScore.getAvg_diff_score();
		this.min_score = schScore.getMin_score();
		this.luqu_count = schScore.getLuqu_count();
		this.luqu_batch = schScore.getLuqu_batch();
	}

	public int getRank_index() {
		return rank_index;
	}

	public void setRank_index(int rank_index) {
		this.rank_index = rank_index;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public int getMin_score() {
		return min_score;
	}

	public void setMin_score(int min_score) {
		this.min_score = min_score;
	}

	public int getLuqu_count() {
		return luqu_count;
	}

	public void setLuqu_count(int luqu_count) {
		this.luqu_count = luqu_count;
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
		result = prime * result + luqu_count;
		result = prime * result + max_score;
		result = prime * result + min_score;
		result = prime * result + rank_index;
		result = prime * result + year;
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
		SchScore other = (SchScore) obj;
		if (avg_diff_score != other.avg_diff_score)
			return false;
		if (avg_score != other.avg_score)
			return false;
		if (luqu_batch == null) {
			if (other.luqu_batch != null)
				return false;
		} else if (!luqu_batch.equals(other.luqu_batch))
			return false;
		if (luqu_count != other.luqu_count)
			return false;
		if (max_score != other.max_score)
			return false;
		if (min_score != other.min_score)
			return false;
		if (rank_index != other.rank_index)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchScore [rank_index=" + rank_index + ", year=" + year + ", max_score=" + max_score + ", avg_score="
				+ avg_score + ", avg_diff_score=" + avg_diff_score + ", min_score=" + min_score + ", luqu_count="
				+ luqu_count + ", luqu_batch=" + luqu_batch + "]";
	}

}
