package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * GenderDist 性别分布.
 * 
 * @author zhongyongsheng
 *
 */
public class GenderDist implements Serializable {
	private static final long serialVersionUID = -5000076385915386166L;
	private int gender;
	private int sample_count;
	private double ratio;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
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
		result = prime * result + gender;
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
		GenderDist other = (GenderDist) obj;
		if (gender != other.gender)
			return false;
		if (Double.doubleToLongBits(ratio) != Double.doubleToLongBits(other.ratio))
			return false;
		if (sample_count != other.sample_count)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenderDist [gender=" + gender + ", sample_count=" + sample_count + ", ratio=" + ratio + "]";
	}

}
