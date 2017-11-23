package com.ipin.service.rest.beans.apply;

import java.util.List;

public class Application {
private String _id;
private String companyId;
private String companyName;
private String beginDate;
private String endDate;
private String website;
private String progress;
private int status;
public String positions;
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
public String getPositions() {
	return positions;
}
public void setPositions(String positions) {
	this.positions = positions;
}
@Override
public String toString() {
	return "Application [_id=" + _id + ", companyId=" + companyId + ", companyName=" + companyName + ", beginDate="
			+ beginDate + ", endDate=" + endDate + ", website=" + website + ", progress=" + progress + ", status="
			+ status + ", positions=" + positions + "]";
}

}
