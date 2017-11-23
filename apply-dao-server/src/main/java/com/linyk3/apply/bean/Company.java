package com.linyk3.apply.bean;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="company")
public class Company {
	private ObjectId _id;
	private String name; // required
	private String companyType; // required
	private String intro; // required
	private String address; // required
	private String telphone; // required
	private String website; // required
	private List<String> employIdList; // required
	private List<String> applicationIdList; // required
	private List<String> teachinIdList; // required
	private List<String> examinationIdList; // required
	private List<String> interviewIdList; // required
	private  String progress;// required
	private String type;
	private int status;
	private String marks; // required
	  public String positions;
	

	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
	
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Company [_id=" + _id + ", name=" + name + ", companyType=" + companyType + ", intro=" + intro
				+ ", address=" + address + ", telphone=" + telphone + ", website=" + website + ", employIdList="
				+ employIdList + ", applicationIdList=" + applicationIdList + ", teachinIdList=" + teachinIdList
				+ ", examinationIdList=" + examinationIdList + ", interviewIdList=" + interviewIdList + ", progress="
				+ progress + ", type=" + type + ", status=" + status + ", marks=" + marks + ", positions=" + positions
				+ "]";
	}


}
