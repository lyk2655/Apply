package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * GenderSchMajorRankingList. 专业排名（学校女生比例排名）.
 * 
 * @author zhongyongsheng
 *
 */
public class GenderSchMajorRankingList extends SchMajorRankingList {

	private static final long serialVersionUID = 8081325500044940350L;

	private double female_ratio;
	private double female_rank;
	private double country_female_ratio;

	public double getFemale_ratio() {
		return female_ratio;
	}

	public void setFemale_ratio(double female_ratio) {
		this.female_ratio = female_ratio;
	}

	public double getFemale_rank() {
		return female_rank;
	}

	public void setFemale_rank(double female_rank) {
		this.female_rank = female_rank;
	}

	public double getCountry_female_ratio() {
		return country_female_ratio;
	}

	public void setCountry_female_ratio(double country_female_ratio) {
		this.country_female_ratio = country_female_ratio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(country_female_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(female_rank);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(female_ratio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GenderSchMajorRankingList other = (GenderSchMajorRankingList) obj;
		if (Double.doubleToLongBits(country_female_ratio) != Double.doubleToLongBits(other.country_female_ratio))
			return false;
		if (Double.doubleToLongBits(female_rank) != Double.doubleToLongBits(other.female_rank))
			return false;
		if (Double.doubleToLongBits(female_ratio) != Double.doubleToLongBits(other.female_ratio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenderSchMajorRankingList [female_ratio=" + female_ratio + ", female_rank=" + female_rank
				+ ", country_female_ratio=" + country_female_ratio + "]";
	}

}
