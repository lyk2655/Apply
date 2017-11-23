package com.ipin.service.rest.beans.apply;

import java.util.List;

public class ExaminationResult {
private List<Examination> examinationList;

public List<Examination> getExaminationList() {
	return examinationList;
}

public void setExaminationList(List<Examination> examinationList) {
	this.examinationList = examinationList;
}

@Override
public String toString() {
	return "ExaminationResult [examinationList=" + examinationList + "]";
}

}
