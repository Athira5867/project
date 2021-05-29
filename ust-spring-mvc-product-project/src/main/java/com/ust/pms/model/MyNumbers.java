package com.ust.pms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MyNumbers {
	@Id
	@GeneratedValue
	private int attempt;
	private int num1;
	private int num2;
	private int num3;
	private int num4;
	private int num5;
	private int result;
	

}
