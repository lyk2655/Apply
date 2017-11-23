package com.ipin.service.rest.beans;

/**
 * CompetitivenessSchRankingList.
 * 竞争力排行榜.
 * 
 * @author zhongyongsheng
 *
 */
public class CompetitivenessSchRankingList extends SchRankingList{

	private static final long serialVersionUID = -2510954796027430813L;
	
	private int competitiveness_rank_index;

	public int getCompetitiveness_rank_index() {
		return competitiveness_rank_index;
	}

	public void setCompetitiveness_rank_index(int competitiveness_rank_index) {
		this.competitiveness_rank_index = competitiveness_rank_index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + competitiveness_rank_index;
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
		CompetitivenessSchRankingList other = (CompetitivenessSchRankingList) obj;
		if (competitiveness_rank_index != other.competitiveness_rank_index)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompetitivenessSchRankingList [competitiveness_rank_index=" + competitiveness_rank_index + "]";
	}
	
}
