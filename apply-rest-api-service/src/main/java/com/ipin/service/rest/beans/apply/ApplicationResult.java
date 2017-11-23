package com.ipin.service.rest.beans.apply;

import java.util.List;

public class ApplicationResult {
private List<Application> applicationList;

public List<Application> getApplicationList() {
	return applicationList;
}

public void setApplicationList(List<Application> applicationList) {
	this.applicationList = applicationList;
}

@Override
public String toString() {
	return "ApplicationResult [applicationList=" + applicationList + "]";
}

}
