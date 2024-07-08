package com.db.dao;

public class AccountQuery {
	public static final String SELECTALLACCOUNTS=
			"SELECT  acc FROM Account acc";
	
	public static final String SELECTACCOUNTBYACCOUNTNUMBER=
			"SELECT  acc FROM Account acc where acc.accountNumber=:accountNumber";
	
	public static final String FINDBALANCEBYACCOUNTNUMBER = "SELECT  acc.balance FROM Account acc where acc.accountNumber=:accountNumber";
}
