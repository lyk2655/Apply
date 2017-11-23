package com.ipin.service.rest.beans.apply;

import java.util.List;

public class TeachinResult {
private List<Teachin> teachinList;

public List<Teachin> getTeachinList() {
	return teachinList;
}

public void setTeachinList(List<Teachin> teachinList) {
	this.teachinList = teachinList;
}

@Override
public String toString() {
	return "TeachinResult [teachinList=" + teachinList + "]";
}

}
