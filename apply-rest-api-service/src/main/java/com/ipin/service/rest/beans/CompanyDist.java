package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * CompanyDist.
 * 公司分布.
 * 
 * @author zhongyongsheng
 *
 */
public class CompanyDist implements Serializable{

	private static final long serialVersionUID = 9072019420370989795L;
	
	private int rank_index;
	private String inc_id;
	private int inc_rank;
	private String industry_id;

	public int getRank_index() {
		return rank_index;
	}

	public void setRank_index(int rank_index) {
		this.rank_index = rank_index;
	}

	public String getInc_id() {
		return inc_id;
	}

	public void setInc_id(String inc_id) {
		this.inc_id = inc_id;
	}

	public int getInc_rank() {
		return inc_rank;
	}

	public void setInc_rank(int inc_rank) {
		this.inc_rank = inc_rank;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inc_id == null) ? 0 : inc_id.hashCode());
		result = prime * result + inc_rank;
		result = prime * result + ((industry_id == null) ? 0 : industry_id.hashCode());
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
		CompanyDist other = (CompanyDist) obj;
		if (inc_id == null) {
			if (other.inc_id != null)
				return false;
		} else if (!inc_id.equals(other.inc_id))
			return false;
		if (inc_rank != other.inc_rank)
			return false;
		if (industry_id == null) {
			if (other.industry_id != null)
				return false;
		} else if (!industry_id.equals(other.industry_id))
			return false;
		if (rank_index != other.rank_index)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompanyDist [rank_index=" + rank_index + ", inc_id=" + inc_id + ", inc_rank=" + inc_rank
				+ ", industry_id=" + industry_id + "]";
	}

}
