package com.linyk3.apply.bean;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="examination")
public class Examination {
	private ObjectId _id;
	private String companyId; // required
	private String companyName;
	private String school; // required
	private String address; // required
	private String date; // required
	private int status; // required
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
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public void setPositions(String positions) {
		this.positions = positions;
	}
	@Override
	public String toString() {
		return "Examination [_id=" + _id + ", companyId=" + companyId + ", companyName=" + companyName + ", school="
				+ school + ", address=" + address + ", date=" + date + ", status=" + status + ", marks=" + marks
				+ ", positions=" + positions + "]";
	}

	
	
}
