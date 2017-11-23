package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * MajorSettingResult. 专业设置返回结果
 * 
 * @author zhongyongsheng
 *
 */
public class SchMajorSetting implements Serializable {

	private static final long serialVersionUID = 4351356070510761190L;
	private String major_name;
	private String major_id;
	private boolean major_has_stats;
	private String major_category;
	private String major_second_category;
	private boolean is_major_id;

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public String getMajor_id() {
		return major_id;
	}

	public void setMajor_id(String major_id) {
		this.major_id = major_id;
	}

	public boolean isMajor_has_stats() {
		return major_has_stats;
	}

	public void setMajor_has_stats(boolean major_has_stats) {
		this.major_has_stats = major_has_stats;
	}

	public String getMajor_category() {
		return major_category;
	}

	public void setMajor_category(String major_category) {
		this.major_category = major_category;
	}

	public String getMajor_second_category() {
		return major_second_category;
	}

	public void setMajor_second_category(String major_second_category) {
		this.major_second_category = major_second_category;
	}

	public boolean isIs_major_id() {
		return is_major_id;
	}

	public void setIs_major_id(boolean is_major_id) {
		this.is_major_id = is_major_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (is_major_id ? 1231 : 1237);
		result = prime * result + ((major_category == null) ? 0 : major_category.hashCode());
		result = prime * result + (major_has_stats ? 1231 : 1237);
		result = prime * result + ((major_id == null) ? 0 : major_id.hashCode());
		result = prime * result + ((major_name == null) ? 0 : major_name.hashCode());
		result = prime * result + ((major_second_category == null) ? 0 : major_second_category.hashCode());
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
		SchMajorSetting other = (SchMajorSetting) obj;
		if (is_major_id != other.is_major_id)
			return false;
		if (major_category == null) {
			if (other.major_category != null)
				return false;
		} else if (!major_category.equals(other.major_category))
			return false;
		if (major_has_stats != other.major_has_stats)
			return false;
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
		if (major_second_category == null) {
			if (other.major_second_category != null)
				return false;
		} else if (!major_second_category.equals(other.major_second_category))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchMajorSetting [major_name=" + major_name + ", major_id=" + major_id + ", major_has_stats="
				+ major_has_stats + ", major_category=" + major_category + ", major_second_category="
				+ major_second_category + ", is_major_id=" + is_major_id + "]";
	}

}
