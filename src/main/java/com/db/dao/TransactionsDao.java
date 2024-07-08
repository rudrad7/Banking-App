package com.db.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.db.entity.Transaction;

@Repository
public interface TransactionsDao extends JpaRepository<Transaction, Long>{
	
	@Query("SELECT  trnx FROM Transaction trnx where trnx.account.accountNumber=:accountNumber order by trnx.timestamp desc")
	List<Transaction> findLast10TransactionsByAccountNumber(@Param("accountNumber") Long accountNumber, Pageable pageable);
}
