package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dao.CustomerDao;
import com.db.entity.Customer;
import com.db.exception.CustomerException;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	
	
	@Override
	public List<Customer> fetchAllCustomers() {
		return customerDao.findAll();
	}

	@Override
	public Customer findByCustId(Long custId) throws CustomerException {
		
		Customer customer = customerDao.findByCustomerId(custId);
		if(customer != null) {
			return customer;
		}
		
		throw new CustomerException("No Customer for the customer id : "+custId);
	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		return customerDao.save(customer);
	}

	@Override
	public Customer findByUserNameAndPassword(String userName, String password) throws CustomerException {
		return customerDao.findByUserNameAndPassword(userName, password);
	}

}
