package com.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.entity.Customer;
import com.db.exception.CustomerException;

public interface CustomerDao extends JpaRepository<Customer, Long> {
	
	
	Customer findByCustomerId(Long custId) throws CustomerException;
	
	Customer findByUserNameAndPassword(String userName, String password) throws CustomerException;

}
