package com.ipin.service.rest.beans;

/**
 * GenderSchRankingList. 性别排行榜（妹纸排行榜）
 * 
 * @author zhongyongsheng
 *
 */
public class GenderSchRankingList extends SchRankingList {

	private static final long serialVersionUID = -9036284035669674888L;

	private double female_ratio;
	private int female_rank_index;

	public double getFemale_ratio() {
		return female_ratio;
	}

	public void setFemale_ratio(double female_ratio) {
		this.female_ratio = female_ratio;
	}

	public int getFemale_rank_index() {
		return female_rank_index;
	}

	public void setFemale_rank_index(int female_rank_index) {
		this.female_rank_index = female_rank_index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + female_rank_index;
		long temp;
		temp = Double.doubleToLongBits(female_ratio);
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
		GenderSchRankingList other = (GenderSchRankingList) obj;
		if (female_rank_index != other.female_rank_index)
			return false;
		if (Double.doubleToLongBits(female_ratio) != Double.doubleToLongBits(other.female_ratio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenderSchRankingList [female_ratio=" + female_ratio + ", female_rank_index=" + female_rank_index + "]";
	}

}
