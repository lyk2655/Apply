package com.linyk3.apply.repository;

import java.util.List;

import com.linyk3.apply.bean.Teachin;
import com.linyk3.thrift.apply.params.ListTeachinInfoParams;

public interface TeachinRepositoryCustom {
	List<Teachin> listTeachinInfo(ListTeachinInfoParams listTeachinInfoParams);
}
