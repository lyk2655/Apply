package com.ipin.service.rest.beans;

/**
 * FamousSchRankingList.
 * 知名度排名.
 * 
 * @author zhongyongsheng
 *
 */
public class FamousSchRankingList extends SchRankingList{

	private static final long serialVersionUID = 6923291074766815844L;
	
	private String famous_rank_str;
	private int famous_rank_index;

	public String getFamous_rank_str() {
		return famous_rank_str;
	}

	public void setFamous_rank_str(String famous_rank_str) {
		this.famous_rank_str = famous_rank_str;
	}

	public int getFamous_rank_index() {
		return famous_rank_index;
	}

	public void setFamous_rank_index(int famous_rank_index) {
		this.famous_rank_index = famous_rank_index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + famous_rank_index;
		result = prime * result + ((famous_rank_str == null) ? 0 : famous_rank_str.hashCode());
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
		FamousSchRankingList other = (FamousSchRankingList) obj;
		if (famous_rank_index != other.famous_rank_index)
			return false;
		if (famous_rank_str == null) {
			if (other.famous_rank_str != null)
				return false;
		} else if (!famous_rank_str.equals(other.famous_rank_str))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FamousSchRankingList [famous_rank_str=" + famous_rank_str + ", famous_rank_index=" + famous_rank_index
				+ "]";
	}

}
