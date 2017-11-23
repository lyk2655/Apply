package com.linyk3.apply.repository;

import java.util.List;

import com.linyk3.apply.bean.Company;
import com.linyk3.thrift.apply.params.ListCompanyInfoParams;

public interface CompanyRepositoryCustom {
	List<Company> listCompanyInfo(ListCompanyInfoParams listCompanyInfoParams);

}
