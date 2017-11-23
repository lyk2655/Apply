package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * LocationDist.
 * LocationDist. 地区分布.
 * 
 * @author zhongyongsheng
 *
 */
public class LocationDist implements Serializable, Comparable<LocationDist> {

	private static final long serialVersionUID = 3411076339812481421L;
	private String city_id;
	private int sample_count;
	private double ratio;

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
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
		result = prime * result + ((city_id == null) ? 0 : city_id.hashCode());
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
		LocationDist other = (LocationDist) obj;
		if (city_id == null) {
			if (other.city_id != null)
				return false;
		} else if (!city_id.equals(other.city_id))
			return false;
		if (Double.doubleToLongBits(ratio) != Double.doubleToLongBits(other.ratio))
			return false;
		if (sample_count != other.sample_count)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationDist [city_id=" + city_id + ", sample_count=" + sample_count + ", ratio=" + ratio + "]";
	}

	@Override
	public int compareTo(LocationDist locationDist) {
		if (this.ratio < locationDist.getRatio()) {
			return 1;
		} else if (this.ratio > locationDist.getRatio()) {
			return -1;
		}
		return 0;
	}

}
