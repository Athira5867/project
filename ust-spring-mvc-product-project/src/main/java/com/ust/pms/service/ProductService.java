package com.ust.pms.service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.pms.model.Product;
import com.ust.pms.repository.ProductRepository;

@Service

public class ProductService 
{
  @Autowired
  ProductRepository productRepository;	
	
	
  
  public void saveProduct(Product product)
  {
	  //This will save the product in database
	  productRepository.save(product);
  }
  
  public List<Product> getProducts()
  {
	  return (List<Product>) productRepository.findAll();
  }
  
  public Product getProduct(Integer productId)
  {
	  Optional<Product> product = productRepository.findById(productId);
	  return product.get();
  }
  public void deleteProduct(Integer productId)
  {
	  productRepository.deleteById(productId);
  }
  public void updateProduct(Product product)
  {
	  //saveOr Update
	  productRepository.save(product);
	  
  }
//To check whether a product is exist with a productId or not
	public boolean isProductExist(int productId) {
		
		return productRepository.existsById(productId);
	}

	public void updateProductQuantity(String productId, int usedQuantity, String action) {
		// TODO Auto-generated method stub
		Product product=getProduct(Integer.parseInt(productId));
		int newQuantity=0;
		if(action.equalsIgnoreCase("Sub")) {
			newQuantity=product.getQuantityOnHand() - usedQuantity;
		}
		else if(action.equalsIgnoreCase("Add")) {
			newQuantity=product.getQuantityOnHand() + usedQuantity;
		}
		product.setQuantityOnHand(newQuantity);
		productRepository.save(product);
		
	}
	public List<Product> searchProductByName(String productName){
		return  productRepository.findByProductName(productName);
	}
  
  
 
}
