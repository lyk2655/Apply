package com.ipin.service.rest.beans;

import java.io.Serializable;

/**
 * PredictSalaryStat. 预测薪酬统计
 * 
 * @author zhongyongsheng
 *
 */
public class PredictSalaryStat implements Serializable, Comparable<PredictSalaryStat> {

	private static final long serialVersionUID = -1040266753972392941L;
	private int grad_year;
	private double salary;
	private boolean virutal_flag;

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

	public boolean isVirutal_flag() {
		return virutal_flag;
	}

	public void setVirutal_flag(boolean virutal_flag) {
		this.virutal_flag = virutal_flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + grad_year;
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (virutal_flag ? 1231 : 1237);
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
		PredictSalaryStat other = (PredictSalaryStat) obj;
		if (grad_year != other.grad_year)
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		if (virutal_flag != other.virutal_flag)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PredictSalaryStat [grad_year=" + grad_year + ", salary=" + salary + ", virutal_flag=" + virutal_flag
				+ "]";
	}

	@Override
	public int compareTo(PredictSalaryStat predictSalaryStat) {
		if (this.grad_year < predictSalaryStat.getGrad_year()) {
			return -1;
		} else if (this.grad_year > predictSalaryStat.getGrad_year()) {
			return 1;
		}
		return 0;
	}

}
