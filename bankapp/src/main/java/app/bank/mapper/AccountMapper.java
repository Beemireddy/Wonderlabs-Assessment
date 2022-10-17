package app.bank.mapper;

import app.bank.domain.AccountDetails;
import app.bank.entity.Accounts;

public class AccountMapper {
	
	private AccountDetails accountDetails;
	
	public void AccountMapper() {
		
	}
	
	public void AccountMapper(AccountDetails accountDetails) {
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
		
		return accounts;
	}

}
