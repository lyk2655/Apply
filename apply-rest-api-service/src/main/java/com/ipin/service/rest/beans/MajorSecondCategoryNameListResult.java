package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * MajorSecondCategoryListResult.
 * 专业二级目录列表结果.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorSecondCategoryNameListResult implements Serializable {

	private static final long serialVersionUID = 8814319580459372494L;
	
	private List<String> major_second_categorys;

	public List<String> getMajor_second_categorys() {
		return major_second_categorys;
	}

	public void setMajor_second_categorys(List<String> major_second_categorys) {
		this.major_second_categorys = major_second_categorys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((major_second_categorys == null) ? 0 : major_second_categorys.hashCode());
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
		MajorSecondCategoryNameListResult other = (MajorSecondCategoryNameListResult) obj;
		if (major_second_categorys == null) {
			if (other.major_second_categorys != null)
				return false;
		} else if (!major_second_categorys.equals(other.major_second_categorys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MajorSecondCategoryListResult [major_second_categorys=" + major_second_categorys + "]";
	}
	
}
