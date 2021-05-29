package com.ust.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ust.pms.model.CustomerData;

public interface CustomerDataRepository  extends CrudRepository<CustomerData, Integer> {

	@Query("SELECT u FROM CustomerData u WHERE u.Username= :Username")
	List<CustomerData> findDataByUsername(@Param("Username") String Username);

}
