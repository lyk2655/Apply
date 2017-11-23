package com.linyk3.apply.repository;

import java.util.List;

import com.linyk3.apply.bean.Interview;
import com.linyk3.thrift.apply.params.ListInterviewInfoParams;

public interface InterviewRepositoryCustom {
	List<Interview> listInterviewInfo(ListInterviewInfoParams listInterviewInfoParams);

}
