package com.linyk3.apply.bean;

import java.util.List;

import org.bson.types.ObjectId;

public class Collection {
	private ObjectId _id; // required
	private List<String> companyIdList; // required
	private List<String> employIdList; // required
	private List<String> applicationIdList; // required
	private List<String> teachinIdList; // required
	private List<String> examinationIdList; // required
	private List<String> interviewIdList; // required
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public List<String> getCompanyIdList() {
		return companyIdList;
	}
	public void setCompanyIdList(List<String> companyIdList) {
		this.companyIdList = companyIdList;
	}
	public List<String> getEmployIdList() {
		return employIdList;
	}
	public void setEmployIdList(List<String> employIdList) {
		this.employIdList = employIdList;
	}
	public List<String> getApplicationIdList() {
		return applicationIdList;
	}
	public void setApplicationIdList(List<String> applicationIdList) {
		this.applicationIdList = applicationIdList;
	}
	public List<String> getTeachinIdList() {
		return teachinIdList;
	}
	public void setTeachinIdList(List<String> teachinIdList) {
		this.teachinIdList = teachinIdList;
	}
	public List<String> getExaminationIdList() {
		return examinationIdList;
	}
	public void setExaminationIdList(List<String> examinationIdList) {
		this.examinationIdList = examinationIdList;
	}
	public List<String> getInterviewIdList() {
		return interviewIdList;
	}
	public void setInterviewIdList(List<String> interviewIdList) {
		this.interviewIdList = interviewIdList;
	}
	@Override
	public String toString() {
		return "Collection [_id=" + _id + ", companyIdList=" + companyIdList + ", employIdList=" + employIdList
				+ ", applicationIdList=" + applicationIdList + ", teachinIdList=" + teachinIdList
				+ ", examinationIdList=" + examinationIdList + ", interviewIdList=" + interviewIdList + "]";
	}

	
}
