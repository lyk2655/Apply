package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * SchScoreListResult.
 * 学校录取分数结果.
 * 
 * @author zhongyongsheng
 *
 */
public class SchScoreListResult implements Serializable{

	private static final long serialVersionUID = -2294559752463034392L;
	
	private List<SchScore> scores;

	public List<SchScore> getScores() {
		return scores;
	}

	public void setScores(List<SchScore> scores) {
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
		SchScoreListResult other = (SchScoreListResult) obj;
		if (scores == null) {
			if (other.scores != null)
				return false;
		} else if (!scores.equals(other.scores))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchScoreListResult [scores=" + scores + "]";
	}
	
}
