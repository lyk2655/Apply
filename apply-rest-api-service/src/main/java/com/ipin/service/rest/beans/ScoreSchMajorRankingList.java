package com.ipin.service.rest.beans;

/**
 * ScoreSchMajorRankingList. 
 * 录取分数学校专业排行榜.
 * 
 * @author zhongyongsheng
 *
 */
public class ScoreSchMajorRankingList extends SchMajorRankingList {

	private static final long serialVersionUID = -8155766271744334063L;

	private int luqu_count;
	private int luqu_socre;
	private double luqu_score_rank;

	public int getLuqu_count() {
		return luqu_count;
	}

	public void setLuqu_count(int luqu_count) {
		this.luqu_count = luqu_count;
	}

	public int getLuqu_socre() {
		return luqu_socre;
	}

	public void setLuqu_socre(int luqu_socre) {
		this.luqu_socre = luqu_socre;
	}

	public double getLuqu_score_rank() {
		return luqu_score_rank;
	}

	public void setLuqu_score_rank(double luqu_score_rank) {
		this.luqu_score_rank = luqu_score_rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + luqu_count;
		long temp;
		temp = Double.doubleToLongBits(luqu_score_rank);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + luqu_socre;
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
		ScoreSchMajorRankingList other = (ScoreSchMajorRankingList) obj;
		if (luqu_count != other.luqu_count)
			return false;
		if (Double.doubleToLongBits(luqu_score_rank) != Double.doubleToLongBits(other.luqu_score_rank))
			return false;
		if (luqu_socre != other.luqu_socre)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScoreSchMajorRankingList [luqu_count=" + luqu_count + ", luqu_socre=" + luqu_socre
				+ ", luqu_score_rank=" + luqu_score_rank + "]";
	}

}
