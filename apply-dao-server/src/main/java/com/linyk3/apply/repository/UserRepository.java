package com.linyk3.apply.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.linyk3.apply.bean.User;

public interface UserRepository extends PagingAndSortingRepository<User, String>{

	User findUserByName(String string);

	List<User> findByNameIn(List<String> nameList);



}
