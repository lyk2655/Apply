package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * MajorNameListResult.
 * 专业名字列表结果.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorNameListResult implements Serializable {

	private static final long serialVersionUID = 8738463208171687344L;

	private List<MajorName> majors;

	public List<MajorName> getMajors() {
		return majors;
	}

	public void setMajors(List<MajorName> majors) {
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
		MajorNameListResult other = (MajorNameListResult) obj;
		if (majors == null) {
			if (other.majors != null)
				return false;
		} else if (!majors.equals(other.majors))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MajorNameListResult [majors=" + majors + "]";
	}
	
}
