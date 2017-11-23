package com.linyk3.apply.bean;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="application")
public class Application {
	private ObjectId _id;
	private String companyId; // required
	private String companyName;
	private String beginDate; // required
	private String endDate; // required
	private String website; // required
	private Integer status; // required
	private String marks; // required
	public String positions;
	  
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
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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
	
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions)  {
		this.positions = positions;
	}
	@Override
	public String toString() {
		return "Application [_id=" + _id + ", companyId=" + companyId + ", companyName=" + companyName + ", beginDate="
				+ beginDate + ", endDate=" + endDate + ", website=" + website + ", status=" + status + ", marks="
				+ marks + ", positions=" + positions + "]";
	}

	
	
}
