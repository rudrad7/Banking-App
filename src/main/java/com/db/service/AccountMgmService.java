package com.db.service;

import java.util.List;

import com.db.entity.Account;
import com.db.exception.AccountException;
import com.db.exception.CustomerException;
import com.db.exception.InsufficientBalanceException;
import com.db.exception.TransactionException;

public interface AccountMgmService {
	
	Double getBalance(Long accountNumber) throws AccountException;
	Account deposit(Long accountNumber, double amount) throws AccountException, TransactionException;
	Account withdraw(Long accountNumber, double amount) throws AccountException, InsufficientBalanceException, TransactionException;
	Account transfer(Long fromAccountNumber,Long toAccountNumber, double amount) throws AccountException, InsufficientBalanceException, TransactionException;
	Account getAccount(Long accountNumber) throws AccountException;
	List<Account> getAllAccounts() throws AccountException;
	
	Account addAccount(String accountType, double balance, Long custId) throws CustomerException, AccountException;
	
}
