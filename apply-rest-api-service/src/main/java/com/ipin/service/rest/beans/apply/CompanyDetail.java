package com.ipin.service.rest.beans.apply;

import java.util.List;

public class CompanyDetail {
	  private String _id; // required
	  private String name; // required
	  private String companyType; // required
	  private String intro; // required
	  private String address; // required
	  private String telphone; // required
	  private String website; // required
//	  private List<String> employIdList; // required
	  private List<Application> applicationList; // required
	  private int applicationListSize;
	  private List<Teachin> teachinList; // required
	  private int teachinListSize;
	  private List<Examination> examinationList; // required
	  private int examinationListSize;
	  private List<Interview> interviewList; // required
	  private int interviewListSize;
	  private String progress; // required
	  private String marks; // required
	  private String positions; // required
	  private int status; // required
	  private String type; // required
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
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
	
	public List<Application> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}
	public List<Teachin> getTeachinList() {
		return teachinList;
	}
	public void setTeachinList(List<Teachin> teachinList) {
		this.teachinList = teachinList;
	}
	public List<Examination> getExaminationList() {
		return examinationList;
	}
	public void setExaminationList(List<Examination> examinationList) {
		this.examinationList = examinationList;
	}
	public List<Interview> getInterviewList() {
		return interviewList;
	}
	public void setInterviewList(List<Interview> interviewList) {
		this.interviewList = interviewList;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getApplicationListSize() {
		return applicationListSize;
	}
	public void setApplicationListSize(int applicationListSize) {
		this.applicationListSize = applicationListSize;
	}
	public int getTeachinListSize() {
		return teachinListSize;
	}
	public void setTeachinListSize(int teachinListSize) {
		this.teachinListSize = teachinListSize;
	}
	public int getExaminationListSize() {
		return examinationListSize;
	}
	public void setExaminationListSize(int examinationListSize) {
		this.examinationListSize = examinationListSize;
	}
	public int getInterviewListSize() {
		return interviewListSize;
	}
	public void setInterviewListSize(int interviewListSize) {
		this.interviewListSize = interviewListSize;
	}
	@Override
	public String toString() {
		return "CompanyDetail [_id=" + _id + ", name=" + name + ", companyType=" + companyType + ", intro=" + intro
				+ ", address=" + address + ", telphone=" + telphone + ", website=" + website + ", applicationList="
				+ applicationList + ", teachinList=" + teachinList + ", examinationList=" + examinationList
				+ ", interviewList=" + interviewList + ", progress=" + progress + ", marks=" + marks + ", positions="
				+ positions + ", status=" + status + ", type=" + type + "]";
	}

	  
	  
}
