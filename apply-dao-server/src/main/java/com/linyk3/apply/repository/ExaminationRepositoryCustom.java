package com.linyk3.apply.repository;

import java.util.List;

import com.linyk3.apply.bean.Examination;
import com.linyk3.thrift.apply.params.ListExaminationInfoParams;

public interface ExaminationRepositoryCustom {
	List<Examination> listExaminationInfo(ListExaminationInfoParams listExaminationInfoParams);

}
