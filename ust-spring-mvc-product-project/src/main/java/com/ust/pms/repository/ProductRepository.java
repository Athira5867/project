package com.ust.pms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ust.pms.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{
	
	List<Product> findByProductName(String productName);
	List<Product> findByPriceGreaterThan(int price);
	List<Product> findByPriceLessThan(int price);
	List<Product> findByPriceBetween(int minRange,int maxRange);
	


}
