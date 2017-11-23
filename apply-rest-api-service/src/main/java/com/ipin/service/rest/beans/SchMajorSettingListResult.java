package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * MajorSettingListResult. 专业设置集合结果
 * 
 * @author zhongyongsheng
 *
 */
public class SchMajorSettingListResult implements Serializable {

	private static final long serialVersionUID = -2163757657094194519L;
	private List<SchMajorSetting> majors;

	public List<SchMajorSetting> getMajors() {
		return majors;
	}

	public void setMajors(List<SchMajorSetting> majors) {
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
		SchMajorSettingListResult other = (SchMajorSettingListResult) obj;
		if (majors == null) {
			if (other.majors != null)
				return false;
		} else if (!majors.equals(other.majors))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchMajorSettingListResult [majors=" + majors + "]";
	}

}
