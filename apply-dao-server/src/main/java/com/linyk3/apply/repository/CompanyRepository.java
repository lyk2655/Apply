package com.linyk3.apply.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.linyk3.apply.bean.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company,String>,CompanyRepositoryCustom {


	Company findByName(String companyName);

	Company findBy_id(ObjectId objectId);


}
