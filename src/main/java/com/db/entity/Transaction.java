package com.db.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction", schema = "digital_bank")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trnx_seq")
	@SequenceGenerator(name = "trnx_seq", sequenceName = "trnx_sequence", allocationSize = 1, schema = "digital_bank")
	@Column(name = "trnx_id")
	private Long trnxId;
	@Column(name = "trnx_type")
	private String type;
	@Column(name = "amount")
	private double amount;
	@Column(name = "remaining_balance")
	private double remainingBalance;
	@Column(name = "timestamp")
	private Date timestamp;
	@Column(name = "status")
	private String status;
	
	@ManyToOne
    @JoinColumn(name = "account_number", nullable = false)
    private Account account;	

	public Transaction() {
	}

	public Transaction(String type, double amount, double remainingBalance, String status, Account account) {
		super();
		this.type = type;
		this.amount = amount;
		this.remainingBalance = remainingBalance;
		this.status = status;
		this.timestamp = new Date();
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public Long getTrnxId() {
		return trnxId;
	}

	public void setTrnxId(Long trnxId) {
		this.trnxId = trnxId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [type=" + type + ", amount=" + amount + ", remainingBalance=" + remainingBalance
				+ ", timestamp=" + timestamp + ", status=" + status +"]";
	}

}
