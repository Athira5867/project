package com.ust.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.pms.model.MyNumbers;
import com.ust.pms.repository.MyNumbersRepository;

@Service

public class MyNumbersService {
	
@Autowired
MyNumbersRepository myNumbersRepository;
public void saveNumbers(MyNumbers myNumbers) {
	//This will save the numbers in databases
	myNumbersRepository.save(myNumbers);
}
}
