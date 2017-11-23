package com.ipin.service.rest.beans.apply;

import java.util.List;

public class Teachin {
	private String _id;
	private String companyId;
	private String companyName;
	private String school;
	private String address;
	private String date;
	private int status;
	private String progress;
	private String marks;
	private String positions;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
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

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
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
		return "Teachin [_id=" + _id + ", companyId=" + companyId + ", companyName=" + companyName + ", school="
				+ school + ", address=" + address + ", date=" + date + ", status=" + status + ", progress=" + progress
				+ ", marks=" + marks + ", positions=" + positions + "]";
	}
	
	

}
