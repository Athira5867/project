package com.ust.pms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity

@Data


public class Cart {
	@Id
	@GeneratedValue
	private int cartId;
	
	private  int productId;
	private String productName;
	private int userId;
    private String username;
    private int quantity;
   private int price;
	

}
