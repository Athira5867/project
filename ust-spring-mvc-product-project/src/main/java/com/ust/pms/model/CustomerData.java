package com.ust.pms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data

public class CustomerData {
	
	@Id
	@GeneratedValue
	private int userId;
	private String Username;
	private String password;
	private String mail;
	
	

}
