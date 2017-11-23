package com.linyk3.apply.bean;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="employ")
public class Employ {
	private ObjectId _id;
	private String companyId; // required
	private String companyName;
	private String positions; // required
	private int number; // required
	private EmployCondition employCondition; // required
	private int salaryMin; // required
	private int salaryMax; // required
	private String benefits; // required
	private String marks; // required
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public EmployCondition getEmployCondition() {
		return employCondition;
	}
	public void setEmployCondition(EmployCondition employCondition) {
		this.employCondition = employCondition;
	}
	public int getSalaryMin() {
		return salaryMin;
	}
	public void setSalaryMin(int salaryMin) {
		this.salaryMin = salaryMin;
	}
	public int getSalaryMax() {
		return salaryMax;
	}
	public void setSalaryMax(int salaryMax) {
		this.salaryMax = salaryMax;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Employ [_id=" + _id + ", companyId=" + companyId + ", companyName=" + companyName + ", positions="
				+ positions + ", number=" + number + ", employCondition=" + employCondition + ", salaryMin=" + salaryMin
				+ ", salaryMax=" + salaryMax + ", benefits=" + benefits + ", marks=" + marks + "]";
	}
	
	

}
