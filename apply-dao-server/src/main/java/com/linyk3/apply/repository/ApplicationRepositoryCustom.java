package com.linyk3.apply.repository;

import java.util.List;

import com.linyk3.apply.bean.Application;
import com.linyk3.thrift.apply.params.ListApplicationInfoParams;

public interface ApplicationRepositoryCustom {
	List<Application> listApplicationInfo(ListApplicationInfoParams listApplicationInfoParams);

}
