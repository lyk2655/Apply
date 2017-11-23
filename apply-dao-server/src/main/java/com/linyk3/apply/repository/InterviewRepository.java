package com.linyk3.apply.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.linyk3.apply.bean.Interview;

public interface InterviewRepository extends PagingAndSortingRepository<Interview,String> ,InterviewRepositoryCustom{


	Interview findBy_id(ObjectId objectId);


}
