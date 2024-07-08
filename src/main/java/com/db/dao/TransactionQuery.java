package com.db.dao;

public class TransactionQuery {
	public static final String SELECTALLTRANSACTIONS = "SELECT  trnx FROM Transaction trnx";
	
	public static final String SELECTALLTRANSACTIONSBYACCONTID = "SELECT  trnx FROM Transaction trnx where trnx.account.accountNumber=:accountNumber";
	
}
