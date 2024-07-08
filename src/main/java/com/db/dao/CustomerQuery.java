package com.db.dao;

public class CustomerQuery {
	
	public static final String SELECTALLCUSTOMERS=
			"SELECT  cust FROM Customer cust";
	
	public static final String SELECTCUSTOMERBYUSERANDPWD=
			"SELECT  cust FROM Customer cust where cust.userName=:userName and cust.password=:pwd";
}
