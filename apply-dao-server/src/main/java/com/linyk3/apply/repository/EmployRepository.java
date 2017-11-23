package com.linyk3.apply.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.linyk3.apply.bean.Employ;

public interface EmployRepository extends PagingAndSortingRepository<Employ,String> ,EmployRepositoryCustom{
	Employ findBy_id(ObjectId objectId);
}
