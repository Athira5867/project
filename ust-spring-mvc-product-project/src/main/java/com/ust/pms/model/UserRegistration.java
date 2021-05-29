package com.ust.pms.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data

public class UserRegistration {
	@Id
	@GeneratedValue
	private int userId;

	private String username;
	private String password;
	private String mail;
	private String confirmpassword;
}
