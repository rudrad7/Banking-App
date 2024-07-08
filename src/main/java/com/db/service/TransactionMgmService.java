package com.db.service;

import java.util.List;

import com.db.entity.Transaction;
import com.db.exception.TransactionException;

public interface TransactionMgmService {
	Transaction addTransactions(Transaction trnx) throws TransactionException;
	List<Transaction> getTransactions(Long accountId)throws TransactionException;
}
