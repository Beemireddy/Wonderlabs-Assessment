package app.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.bank.domain.AccountDetails;
import app.bank.entity.Accounts;
import app.bank.entity.TransactionHistory;
import app.bank.mapper.AccountMapper;
import app.bank.mapper.TransactionHistoryMapper;
import app.bank.repository.CreateAccountRepository;
import app.bank.repository.TransactionHistoryRepository;

@Service
public class TransactionHistoryServiceImpl {
	
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;
	
	public void saveTransactionHistory(Accounts accounts) {
		
		TransactionHistoryMapper historymapper = new TransactionHistoryMapper();
		TransactionHistory history = historymapper.mapAccountsEntityToTransHistoryEntity(accounts);
		
		transactionHistoryRepository.save(history);
		System.out.println("End of transactionHistory - audit created");
	}

}
