package com.ipin.service.test.service.test.beans;

import java.util.List;

import com.ipin.service.rest.beans.ScoreSchMajorRankingList;

public class ScoreSchMajorRankingListResult {
	
	private List<ScoreSchMajorRankingList> majors;

	public List<ScoreSchMajorRankingList> getMajors() {
		return majors;
	}

	public void setMajors(List<ScoreSchMajorRankingList> majors) {
		this.majors = majors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((majors == null) ? 0 : majors.hashCode());
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
		ScoreSchMajorRankingListResult other = (ScoreSchMajorRankingListResult) obj;
		if (majors == null) {
			if (other.majors != null)
				return false;
		} else if (!majors.equals(other.majors))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScoreSchMajorRankingListResult [majors=" + majors + "]";
	}
	
}
