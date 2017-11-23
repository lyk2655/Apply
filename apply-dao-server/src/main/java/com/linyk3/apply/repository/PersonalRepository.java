package com.linyk3.apply.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.linyk3.apply.bean.Personal;

public interface PersonalRepository extends PagingAndSortingRepository<Personal,String> {

	Personal findByName(String name);

	List<Personal> findByNameIn(List<String> nameList);

	Personal findBy_id(ObjectId objectId);

}
