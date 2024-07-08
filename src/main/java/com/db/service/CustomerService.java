package com.db.service;

import java.util.List;

import com.db.entity.Customer;
import com.db.exception.CustomerException;

public interface CustomerService {
	List<Customer> fetchAllCustomers();

	Customer findByCustId(Long custId) throws CustomerException;

	Customer addCustomer(Customer customer) throws CustomerException;
	
	Customer findByUserNameAndPassword(String userName, String password) throws CustomerException;	
}
