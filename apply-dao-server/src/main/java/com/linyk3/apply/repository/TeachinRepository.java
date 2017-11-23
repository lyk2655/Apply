package com.linyk3.apply.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.linyk3.apply.bean.Teachin;

public interface TeachinRepository extends PagingAndSortingRepository<Teachin,String>,TeachinRepositoryCustom {


	Teachin findBy_id(ObjectId objectId);

}
