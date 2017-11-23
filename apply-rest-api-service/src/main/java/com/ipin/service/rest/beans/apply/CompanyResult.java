package com.ipin.service.rest.beans.apply;

import java.util.List;

public class CompanyResult {
	
	private List<Company> companyList;

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	@Override
	public String toString() {
		return "CompanyResult [companyList=" + companyList + "]";
	}

}
