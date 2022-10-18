package app.bank.mapper;

import app.bank.domain.AccountDetails;
import app.bank.entity.Accounts;

public class AccountMapper {
	
	private AccountDetails accountDetails;
	
	public AccountMapper() {
		
	}
	
	public AccountMapper(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}
	
	public Accounts mapAccountDomainToEntity(AccountDetails accountDetails) {
		
		Accounts accounts = new Accounts();
		accounts.setApplicationNumber(accountDetails.getApplicationNumber());
		accounts.setAccountBalance(accountDetails.getAccountBalance());
		accounts.setAccountName(accountDetails.getAccountName());
		accounts.setAccountType(accountDetails.getAccountType());
		accounts.setAddress(accountDetails.getAddress());
		accounts.setOverDraft(accountDetails.getOverDraft());
		accounts.setCreatedTime(accountDetails.getCreatedTime());
		
		return accounts;
	}
	
public AccountDetails mapEntityToAccountDetails(Accounts accounts) {
		
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setApplicationNumber(accounts.getApplicationNumber());
		accountDetails.setAccountBalance(accounts.getAccountBalance());
		accountDetails.setAccountName(accounts.getAccountName());
		accountDetails.setAccountType(accounts.getAccountType());
		accountDetails.setAddress(accounts.getAddress());
		accountDetails.setOverDraft(accounts.getOverDraft());
		accountDetails.setAccountNumber(accounts.getAccountNumber());
		accountDetails.setCreatedTime(accounts.getCreatedTime());
		return accountDetails;
	}

}
