package com.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.db.entity.Account;
import com.db.exception.AccountException;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>{
	
	Account findByAccountNumber(Long accountNumber);
	@Query("select a.balance from Account a where a.accountNumber=:accountId")
	Double getBalance(Long accountId)throws AccountException;
}
