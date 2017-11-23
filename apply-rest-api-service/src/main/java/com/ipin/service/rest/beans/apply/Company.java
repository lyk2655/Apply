package com.ipin.service.rest.beans.apply;

import java.util.List;

public class Company {
	private String _id;
	private String name;
	private String type;
	private int status;
	private String progress;
	private String positions;
	private String address;
	private String website;



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

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "Company [_id=" + _id + ", name=" + name + ", type=" + type + ", status=" + status + ", progress="
				+ progress + ", positions=" + positions + ", address=" + address + ", website=" + website + "]";
	}

	


}
