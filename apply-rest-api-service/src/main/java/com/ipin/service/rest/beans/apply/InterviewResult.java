package com.ipin.service.rest.beans.apply;

import java.util.List;

public class InterviewResult {
private List<Interview> interviewList;

public List<Interview> getInterviewList() {
	return interviewList;
}

public void setInterviewList(List<Interview> interviewList) {
	this.interviewList = interviewList;
}

@Override
public String toString() {
	return "InterviewResult [interviewList=" + interviewList + "]";
}

}
