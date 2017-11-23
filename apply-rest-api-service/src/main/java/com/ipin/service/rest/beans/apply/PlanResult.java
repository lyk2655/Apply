package com.ipin.service.rest.beans.apply;

import java.util.List;

public class PlanResult {
	private String name;
	private String timeFilter;
	private String typeFilter;
	private List<Plan> planList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimeFilter() {
		return timeFilter;
	}
	public void setTimeFilter(String timeFilter) {
		this.timeFilter = timeFilter;
	}
	public String getTypeFilter() {
		return typeFilter;
	}
	public void setTypeFilter(String typeFilter) {
		this.typeFilter = typeFilter;
	}
	public List<Plan> getPlanList() {
		return planList;
	}
	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}
	@Override
	public String toString() {
		return "PlanResult [name=" + name + ", timeFilter=" + timeFilter + ", typeFilter=" + typeFilter + ", planList="
				+ planList + "]";
	}
	
	
}
