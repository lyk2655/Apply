package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * MajorName.
 * 专业名字.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorName implements Serializable {

	private static final long serialVersionUID = 4814518364179027075L;

	private String major_id;
	private String major_name;

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((major_id == null) ? 0 : major_id.hashCode());
		result = prime * result + ((major_name == null) ? 0 : major_name.hashCode());
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
		MajorName other = (MajorName) obj;
		if (major_id == null) {
			if (other.major_id != null)
				return false;
		} else if (!major_id.equals(other.major_id))
			return false;
		if (major_name == null) {
			if (other.major_name != null)
				return false;
		} else if (!major_name.equals(other.major_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MajorName [major_id=" + major_id + ", major_name=" + major_name + "]";
	}
	
}
