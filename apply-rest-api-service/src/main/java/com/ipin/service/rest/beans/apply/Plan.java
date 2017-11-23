package com.ipin.service.rest.beans.apply;

public class Plan {
	private String _id;
	private String date;
	private String companyId;
	private String companyName;
	private String type;
	private int status;
	private String progress;
	private String address;
	private String positions;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
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
	@Override
	public String toString() {
		return "Plan [_id=" + _id + ", date=" + date  + ", companyId=" + companyId + ", companyName="
				+ companyName + ", type=" + type + ", status=" + status + ", progress=" + progress + ", address="
				+ address + ", positions=" + positions + "]";
	}



}
