package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchMajorScoreListResult.
 * 学校专业录取分列表结果.
 * 
 * @author zhongyongsheng
 *
 */
public class SchMajorScoreListResult implements Serializable{

	private static final long serialVersionUID = -794268143942179714L;
	
	private List<SchMajorScore> scores;

	public List<SchMajorScore> getScores() {
		return scores;
	}

	public void setScores(List<SchMajorScore> scores) {
		this.scores = scores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((scores == null) ? 0 : scores.hashCode());
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
		SchMajorScoreListResult other = (SchMajorScoreListResult) obj;
		if (scores == null) {
			if (other.scores != null)
				return false;
		} else if (!scores.equals(other.scores))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchMajorScoreListResult [scores=" + scores + "]";
	}
	
}
