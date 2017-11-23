package com.linyk3.apply.bean;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EmployCondition {
	private List<String> diplomas; // required
	private List<String> schools; // required
	private List<String> majors; // required
	private String others; // required
	public List<String> getDiplomas() {
		return diplomas;
	}
	public void setDiplomas(List<String> diplomas) {
		this.diplomas = diplomas;
	}
	public List<String> getSchools() {
		return schools;
	}
	public void setSchools(List<String> schools) {
		this.schools = schools;
	}
	public List<String> getMajors() {
		return majors;
	}
	public void setMajors(List<String> majors) {
		this.majors = majors;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	@Override
	public String toString() {
		return "EmployCondition [diplomas=" + diplomas + ", schools=" + schools + ", majors=" + majors + ", others="
				+ others + "]";
	}
	
	
}
