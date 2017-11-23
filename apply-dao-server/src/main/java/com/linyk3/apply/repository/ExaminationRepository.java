package com.linyk3.apply.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.linyk3.apply.bean.Examination;

public interface ExaminationRepository extends PagingAndSortingRepository<Examination,String> ,ExaminationRepositoryCustom{


	Examination findBy_id(ObjectId objectId);
	
}
