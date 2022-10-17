package app.bank.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Accounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountNumber;
	
	@NotNull
	private long applicationNumber;
	@NotNull
	private String accountName;
	@NotNull
	private String accountType;
	@NotNull
	private double accountBalance;
	@NotNull
	private double overDraft;
	@NotNull
	private LocalDateTime createdTime;
	@NotNull
	private String address;

	public Accounts() {

	}

	public Accounts(long applicationNumber, String accountName, String accountType, double accountBalance, 
			double overDraft, String address) {
		this.applicationNumber = applicationNumber;
		this.accountName = accountName;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.overDraft = overDraft;
		this.address = address;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
