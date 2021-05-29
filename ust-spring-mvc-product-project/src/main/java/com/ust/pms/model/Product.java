package com.ust.pms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Getter
//@Setter
@NoArgsConstructor//default
@ToString
@EqualsAndHashCode
@Data
@RequiredArgsConstructor//default
@AllArgsConstructor


//we can use @Data to get all getters and setters
//we have @RequiredArgsConstructor to create a parametrised constructor. if we use with no_args it will show error, duplicating method
//to remove that error we can use @NonNull with the variables
public class Product {
	@Id
	private @NonNull int productId;
	private  String productName;
	private @NonNull int quantityOnHand;
	private @NonNull int price;
	
}
	/*public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantityOnHand() {
		return quantityOnHand;
	}
	public void setQuantityOnHand(int quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	private int price;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*public Product()
	{
		
	}*/
/*	public Product(int productId, String productName, int quantityOnHand, int price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantityOnHand = quantityOnHand;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", quantityOnHand=" + quantityOnHand
				+ ", price=" + price + "]";
	}
	

}*/
