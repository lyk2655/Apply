package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * SalaryStat.
 * 薪酬统计
 * 
 * @author zhongyongsheng
 *
 */
public class SalaryStat implements Serializable, Comparable<SalaryStat>{

	private static final long serialVersionUID = 6886501829816725160L;
	private int grad_year;
	private double salary;
	private int sample_count;
	
	public int getGrad_year() {
		return grad_year;
	}
	
	public void setGrad_year(int grad_year) {
		this.grad_year = grad_year;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public int getSample_count() {
		return sample_count;
	}
	
	public void setSample_count(int sample_count) {
		this.sample_count = sample_count;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + grad_year;
		long temp;
		temp = Double.doubleToLongBits(salary);
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
		SalaryStat other = (SalaryStat) obj;
		if (grad_year != other.grad_year)
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (sample_count != other.sample_count)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SalaryStats [grad_year=" + grad_year + ", salary=" + salary + ", sample_count=" + sample_count + "]";
	}

	@Override
	public int compareTo(SalaryStat salaryStat) {
		if(this.grad_year < salaryStat.getGrad_year()) {
			return -1;
		} else if (this.grad_year > salaryStat.getGrad_year()) {
			return 1;
		}
		return 0;
	}
	

}
