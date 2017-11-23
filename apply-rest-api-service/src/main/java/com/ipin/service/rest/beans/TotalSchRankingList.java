package com.ipin.service.rest.beans;

/**
 * TotalSchRankingList.
 * 总排名（就业综合排名）.
 * 
 * @author zhongyongsheng
 *
 */
public class TotalSchRankingList extends SchRankingList{

	private static final long serialVersionUID = -1470323694575055485L;
	
	private String total_rank_str;
	private int total_rank_index;

	public String getTotal_rank_str() {
		return total_rank_str;
	}

	public void setTotal_rank_str(String total_rank_str) {
		this.total_rank_str = total_rank_str;
	}

	public int getTotal_rank_index() {
		return total_rank_index;
	}

	public void setTotal_rank_index(int total_rank_index) {
		this.total_rank_index = total_rank_index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + total_rank_index;
		result = prime * result + ((total_rank_str == null) ? 0 : total_rank_str.hashCode());
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
		TotalSchRankingList other = (TotalSchRankingList) obj;
		if (total_rank_index != other.total_rank_index)
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
		return "TotalSchRankingList [total_rank_str=" + total_rank_str + ", total_rank_index=" + total_rank_index + "]";
	}

}
