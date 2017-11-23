package com.linyk3.apply.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.linyk3.apply.bean.Application;
import com.linyk3.thrift.apply.params.ListApplicationInfoParams;

public interface ApplicationRepository extends PagingAndSortingRepository<Application,String>,ApplicationRepositoryCustom {


	Application findBy_id(ObjectId objectId);


}
