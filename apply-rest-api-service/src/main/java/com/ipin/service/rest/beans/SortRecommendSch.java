package com.ipin.service.rest.beans;

/**
 * SortRecommendSchool.
 * 排序推荐学校.
 * 
 * @author zhongyongsheng
 *
 */
public class SortRecommendSch implements Comparable<SortRecommendSch>{
	
	private String schId;
	private double salaryFactor;
	private double totalRank;

	public String getSchId() {
		return schId;
	}

	public void setSchId(String schId) {
		this.schId = schId;
	}

	public double getSalaryFactor() {
		return salaryFactor;
	}

	public void setSalaryFactor(double salaryFactor) {
		this.salaryFactor = salaryFactor;
	}

	public double getTotalRank() {
		return totalRank;
	}

	public void setTotalRank(double totalRank) {
		this.totalRank = totalRank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(salaryFactor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((schId == null) ? 0 : schId.hashCode());
		temp = Double.doubleToLongBits(totalRank);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		SortRecommendSch other = (SortRecommendSch) obj;
		if (Double.doubleToLongBits(salaryFactor) != Double.doubleToLongBits(other.salaryFactor))
			return false;
		if (schId == null) {
			if (other.schId != null)
				return false;
		} else if (!schId.equals(other.schId))
			return false;
		if (Double.doubleToLongBits(totalRank) != Double.doubleToLongBits(other.totalRank))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SortRecommendSchool [schId=" + schId + ", salaryFactor=" + salaryFactor + ", totalRank=" + totalRank
				+ "]";
	}

	@Override
	public int compareTo(SortRecommendSch sortRecommendSchool) {
		if (salaryFactor < sortRecommendSchool.getSalaryFactor()) {
			return 1;
		} else if (salaryFactor > sortRecommendSchool.getSalaryFactor()) {
			return -1;
		} else if (totalRank < sortRecommendSchool.getTotalRank()) {
			return 1;
		} else if (totalRank > sortRecommendSchool.getTotalRank()) {
			return -1;
		}
		return 0;
	}

}
