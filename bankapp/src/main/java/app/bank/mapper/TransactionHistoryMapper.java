package app.bank.mapper;

import app.bank.entity.Accounts;
import app.bank.entity.TransactionHistory;

public class TransactionHistoryMapper {
	
	private TransactionHistory history;
	
	public TransactionHistoryMapper() {
		
	}
	
	public TransactionHistoryMapper(TransactionHistory history) {
		this.history = history;
	}
	
	public TransactionHistory mapAccountsEntityToTransHistoryEntity(Accounts accounts) {
		
		TransactionHistory history = new TransactionHistory();
		history.setTransactionAccount(accounts.getAccountNumber());
		history.setTransactionAmount(accounts.getAccountBalance());
		history.setTransactionType(accounts.getAccountType());
		history.setTransactionDate(accounts.getCreatedTime());
		
		return history;
	}
}
