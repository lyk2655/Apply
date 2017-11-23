package com.ipin.service.rest.beans;

/**
 * 学校录取分数额外字段
 * @author zhongyongsheng
 *
 */
public class SchScoreDetail extends SchScore{
	
	private static final long serialVersionUID = -9177300282467948520L;
	
	private String provinceId;
	
	public String getProvinceId() {
		return provinceId;
	}
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchScoreDetail other = (SchScoreDetail) obj;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchScoreDetail [provinceId=" + provinceId + "]";
	}
	
}
