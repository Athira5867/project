package com.ust.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.pms.ust.email.EmailService;
import com.ust.pms.model.Cart;
import com.ust.pms.model.CustomerData;
import com.ust.pms.model.UserRegistration;
import com.ust.pms.repository.CartRepository;
import com.ust.pms.repository.CustomerDataRepository;

//import com.ust.pms.repository.CustomerDataRepository;
@Service
public class CustomerDataService {

	@Autowired
	CustomerDataRepository customerDataRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	EmailService emailService;

	public void saveUserData(UserRegistration userRegistration) {
		CustomerData userData = new CustomerData();
		userData.setUsername(userRegistration.getUsername());
		userData.setMail(userRegistration.getMail());
		userData.setPassword(userRegistration.getPassword());

		customerDataRepository.save(userData);
	}

	public List<CustomerData> findByUsername(String username) {
		return customerDataRepository.findDataByUsername(username);
	}

	public String getUserFromPrincipal() {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		return username;
	}
	/*
	 * public void sendCartMail(List<Cart> cartItems) {
	 * 
	 * CustomerData customer=getCustomerDetails();
	 * emailService.sendCartDetails(cartItems,customer.getMail(),"PCART DETAILS"
	 * ,customer.getUsername());
	 * 
	 * }
	 * 
	 * public CustomerData getCustomerDetails() { String username=null;
	 * //CustomerDataService userdataservice = null;
	 * //username=userdataservice.getUserFromPrincipal(); return (CustomerData)
	 * customerDataRepository.findDataByUsername(username);
	 * 
	 * }
	 */

}
