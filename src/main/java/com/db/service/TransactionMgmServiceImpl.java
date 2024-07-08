package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.db.dao.TransactionsDao;
import com.db.entity.Transaction;
import com.db.exception.TransactionException;

@Service
public class TransactionMgmServiceImpl implements TransactionMgmService {
	@Autowired
	private TransactionsDao transactionsDao;

	@Override
	public List<Transaction> getTransactions(Long accountNumber) throws TransactionException {
		/*
		 * List<Transaction> all = transactionsDao.findAll();
		 * System.out.println("All Transactions "+ all); return all; .stream()
		 * .filter(trnx -> accountNumber.equals(trnx.getAccount().getAccountNumber()))
		 * .collect(Collectors.toList());
		 */
		return transactionsDao.findLast10TransactionsByAccountNumber(accountNumber, PageRequest.of(0, 10));
	}

	@Override
	public Transaction addTransactions(Transaction trnx) throws TransactionException {
		return transactionsDao.save(trnx);
	}

}
