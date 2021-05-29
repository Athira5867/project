package com.ust.pms.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ust.pms.model.MyNumbers;


public interface MyNumbersRepository extends CrudRepository<MyNumbers, Integer> {
	

}
