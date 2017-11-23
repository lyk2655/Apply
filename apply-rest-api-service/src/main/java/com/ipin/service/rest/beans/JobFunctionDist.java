package com.ipin.service.rest.beans;

import java.io.Serializable;
import java.util.List;

/**
 * JobFunctionDist.
 * 职能分布.
 * 
 * @author zhongyongsheng
 *
 */
public class JobFunctionDist implements Serializable, Comparable<JobFunctionDist>{

	private static final long serialVersionUID = 5878396112095916227L;
	
	private String zhineng;
	private int sample_count;
	private double ratio;
	private List<String> position_list;
	private List<String> industry_list;

	public String getZhineng() {
		return zhineng;
	}

	public void setZhineng(String zhineng) {
		this.zhineng = zhineng;
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

	public List<String> getPosition_list() {
		return position_list;
	}

	public void setPosition_list(List<String> position_list) {
		this.position_list = position_list;
	}

	public List<String> getIndustry_list() {
		return industry_list;
	}

	public void setIndustry_list(List<String> industry_list) {
		this.industry_list = industry_list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((industry_list == null) ? 0 : industry_list.hashCode());
		result = prime * result + ((position_list == null) ? 0 : position_list.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sample_count;
		result = prime * result + ((zhineng == null) ? 0 : zhineng.hashCode());
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
		JobFunctionDist other = (JobFunctionDist) obj;
		if (industry_list == null) {
			if (other.industry_list != null)
				return false;
		} else if (!industry_list.equals(other.industry_list))
			return false;
		if (position_list == null) {
			if (other.position_list != null)
				return false;
		} else if (!position_list.equals(other.position_list))
			return false;
		if (Double.doubleToLongBits(ratio) != Double.doubleToLongBits(other.ratio))
			return false;
		if (sample_count != other.sample_count)
			return false;
		if (zhineng == null) {
			if (other.zhineng != null)
				return false;
		} else if (!zhineng.equals(other.zhineng))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JobFunctionDist [zhineng=" + zhineng + ", sample_count=" + sample_count + ", ratio=" + ratio
				+ ", position_list=" + position_list + ", industry_list=" + industry_list + "]";
	}

	@Override
	public int compareTo(JobFunctionDist jobFunctionDist) {
		if (this.getRatio() < jobFunctionDist.getRatio()) {
			return 1;
		} else if (this.getRatio() > jobFunctionDist.getRatio()) {
			return -1;
		}
		return 0;
	}

}
