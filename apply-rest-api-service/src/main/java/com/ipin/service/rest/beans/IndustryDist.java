package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * IndustryDist. 行业分布
 * 
 * @author zhongyongsheng
 *
 */
public class IndustryDist implements Serializable, Comparable<IndustryDist> {
	private static final long serialVersionUID = -5148501547090574744L;
	private String industry_id;
	private int sample_count;
	private double ratio;

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public int getSample_count() {
		return sample_count;
	}

	public void setSample_count(int sample_count) {
		this.sample_count = sample_count;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((industry_id == null) ? 0 : industry_id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sample_count;
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
		IndustryDist other = (IndustryDist) obj;
		if (industry_id == null) {
			if (other.industry_id != null)
				return false;
		} else if (!industry_id.equals(other.industry_id))
			return false;
		if (Double.doubleToLongBits(ratio) != Double.doubleToLongBits(other.ratio))
			return false;
		if (sample_count != other.sample_count)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IndustryDist [industry_id=" + industry_id + ", sample_count=" + sample_count + ", ratio=" + ratio + "]";
	}

	@Override
	public int compareTo(IndustryDist industryDist) {
		if (this.ratio < industryDist.getRatio()) {
			return 1;
		} else if (this.ratio > industryDist.getRatio()) {
			return -1;
		}
		return 0;
	}

}
