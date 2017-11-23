package com.linyk3.apply.repository;

import java.util.List;

import com.linyk3.apply.bean.Employ;
import com.linyk3.thrift.apply.params.ListEmployInfoParams;

public interface EmployRepositoryCustom {
	List<Employ> listEmployInfo(ListEmployInfoParams listEmployInfoParams);

}
