
package com.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dao.AccountDao;
import com.db.entity.Account;
import com.db.entity.Customer;
import com.db.entity.Transaction;
import com.db.exception.AccountException;
import com.db.exception.CustomerException;
import com.db.exception.TransactionException;

@Service
public class AccountMgmServiceImpl implements AccountMgmService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private TransactionMgmService transactionMgmService;

	@Autowired
	private CustomerService customerService;

	@Override
	public Double getBalance(Long accountNumber) throws AccountException {
		Double balance = accountDao.getBalance(accountNumber);
		if (balance != null)
			return balance;
		throw new AccountException("Account not found for account Number : " + accountNumber);
	}

	@Override
	public Account deposit(Long accountNumber, double amount) throws AccountException, TransactionException {
		validateAmount(amount);
		Account account = getAccount(accountNumber);
		account.setBalance(amount + account.getBalance());
		accountDao.save(account);
		Transaction transaction = new Transaction("Deposit", amount, account.getBalance(), "Success", account);
		transactionMgmService.addTransactions(transaction);

		return account;
	}

	private void validateAmount(double amount) throws AccountException {
		if ((amount <= 0)) {
			throw new AccountException("Amount should be more than 0 and Should not be negative number");
		}
	}

	@Override
	public Account withdraw(Long accountNumber, double amount) throws AccountException, TransactionException {
		validateAmount(amount);
		Account account = getAccount(accountNumber);
		if (amount <= account.getBalance()) {
			account.setBalance(account.getBalance() - amount);
			accountDao.save(account);
			Transaction transaction = new Transaction("Withdraw", amount, account.getBalance(), "Success", account);
			transactionMgmService.addTransactions(transaction);
			return account;
		}

		throw new AccountException("Insufficient Balance for Account Id " + accountNumber);
	}

	@Override
	public Account getAccount(Long accountNumber) throws AccountException {
		Account account = accountDao.findByAccountNumber(accountNumber);
		if (account != null) {
			return account;
		}
		throw new AccountException("Account not found with the account number " + accountNumber);
	}

	@Override
	public List<Account> getAllAccounts() throws AccountException {
		return accountDao.findAll();
	}

	@Override
	public Account addAccount(String accountType, double balance, Long custId)
			throws CustomerException, AccountException {
		validateAmount(balance);
		Customer cust = customerService.findByCustId(custId);
		return accountDao.save(new Account(accountType, balance, cust));
	}

	@Override
	public Account transfer(Long fromAccountNumber, Long toAccountNumber, double amount)
			throws AccountException, TransactionException {
		validateAmount(amount);
		Account sourceAccount = getAccount(fromAccountNumber);

		Account destinationAccount = getAccount(toAccountNumber);
		if (amount <= sourceAccount.getBalance()) {
			sourceAccount.setBalance(sourceAccount.getBalance() - amount);
			destinationAccount.setBalance(amount + destinationAccount.getBalance());
			updateData(sourceAccount,
					new Transaction("Transfer", amount, sourceAccount.getBalance(), "Success", sourceAccount));
			updateData(destinationAccount, new Transaction("Transfer", amount, destinationAccount.getBalance(),
					"Success", destinationAccount));

			return sourceAccount;
		}
		throw new AccountException("Insufficient Balance for Account Id " + sourceAccount.getAccountNumber());
	}

	private void updateData(Account account, Transaction transaction) throws AccountException, TransactionException {
		accountDao.save(account);
		transactionMgmService.addTransactions(transaction);

	}

}
