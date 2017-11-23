package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * MajorFilterParamsResult.
 * 获取专业过滤参数结果.
 * 
 * @author zhongyongsheng
 *
 */
public class MajorFilterParamsResult implements Serializable{

	private static final long serialVersionUID = 3836318710949881437L;
	
	private List<String> categorys;

	public List<String> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorys == null) ? 0 : categorys.hashCode());
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
		MajorFilterParamsResult other = (MajorFilterParamsResult) obj;
		if (categorys == null) {
			if (other.categorys != null)
				return false;
		} else if (!categorys.equals(other.categorys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MajorFilterParamsResult [categorys=" + categorys + "]";
	}

}
