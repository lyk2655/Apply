package com.ipin.service.rest.beans.apply;

import java.util.Date;

public class PlanMail {
	String type;
	private String _id;
	private String date;
	private String companyName;
	private int status;
	private String address;
	private String positions;
	private String subject;
	private Date sendTime;
	private String marks;
	private String userName;
	private String email;
	
	public PlanMail() {
		super();
	}
	public PlanMail(String type, String _id, String date, String companyName, int status, String address,
			String positions, String subject, Date sendTime, String marks,String userName, String email) {
		super();
		this.type = type;
		this._id = _id;
		this.date = date;
		this.companyName = companyName;
		this.status = status;
		this.address = address;
		this.positions = positions;
		this.subject = subject;
		this.sendTime = sendTime;
		this.marks = marks;
		this.userName = userName;
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "PlanMail [type=" + type + ", _id=" + _id + ", date=" + date + ", companyName=" + companyName
				+ ", status=" + status + ", address=" + address + ", positions=" + positions + ", subject=" + subject
				+ ", sendTime=" + sendTime + ", marks=" + marks + ", userName=" + userName + ", email=" + email + "]";
	}

	
	
}
