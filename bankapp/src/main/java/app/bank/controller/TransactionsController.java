package app.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.bank.domain.AccountDetails;
import app.bank.domain.Withdraw;
import app.bank.entity.Accounts;
import app.bank.mapper.AccountMapper;
import app.bank.repository.TransactionsRepository;
import app.bank.service.TransactionHistoryServiceImpl;
import app.bank.util.AccountType;

@RestController
@RequestMapping("api")
public class TransactionsController {

	private AccountMapper accountMapper;

	@Autowired
	private TransactionsRepository transactionsRepository;

	@Autowired
	private TransactionHistoryServiceImpl transactionHistoryServiceImpl;

	private AccountDetails accountDetails;

	private double ODLimit = 100000;

	@PutMapping("/account/deposits/{id}")
	public AccountDetails deposit(@PathVariable(value = "id") Long accountId, @RequestParam("amount") double amount) {

		Optional<Accounts> acctsOpt = transactionsRepository.findById(accountId);
		if(acctsOpt.isPresent()) {
			Accounts accounts = acctsOpt.get();
			if(accounts.getAccountType().equalsIgnoreCase(AccountType.SAVINGS.toString())) {
				double updatedbal = accounts.getAccountBalance() + amount;
				accounts.setAccountBalance(updatedbal);
			}
			if(accounts.getAccountType().equalsIgnoreCase(AccountType.CURRENT.toString())) {
				if(accounts.getOverDraft() < ODLimit) {
					double availODAmt = accounts.getOverDraft(); // 98500
					double balODAmt = ODLimit - availODAmt; // 1500
					double depActBal = amount - balODAmt; // 2000 - 1500 = 500
					if(depActBal < 0) {
						accounts.setAccountBalance(0);
						accounts.setOverDraft(availODAmt + amount);
					}else {
						accounts.setAccountBalance(depActBal);
						accounts.setOverDraft(availODAmt + balODAmt);
					}
				}else {
					double updatedbal = accounts.getAccountBalance() + amount;
					accounts.setAccountBalance(updatedbal);
				}

			}

			accounts = transactionsRepository.save(accounts);

			transactionHistoryServiceImpl.saveTransactionHistory(accounts);

			accountMapper = new AccountMapper();
			return accountMapper.mapEntityToAccountDetails(accounts);
		}

		return accountDetails;
	}

	@PostMapping("/account/withdraw")
	public AccountDetails withdrawal(@RequestBody Withdraw withdraw) throws Exception {

		AccountDetails accountDetails = new AccountDetails();
		Optional<Accounts> acctsOpt = transactionsRepository.findById(withdraw.getAccountNumber());
		if(acctsOpt.isPresent()) {
			Accounts accounts = acctsOpt.get();

			if (accounts.getAccountType().equalsIgnoreCase(AccountType.SAVINGS.toString())) {
				double remainingBal = accounts.getAccountBalance() - withdraw.getAmount();
				if(remainingBal < 1000) {
					throw new Exception("Insufficient Balance");
				}
				accounts.setAccountBalance(remainingBal);
				accounts = transactionsRepository.save(accounts);

				transactionHistoryServiceImpl.saveTransactionHistory(accounts);

				accountMapper = new AccountMapper();
				return accountMapper.mapEntityToAccountDetails(accounts);

			}else {
				double remainingBal = accounts.getAccountBalance() - withdraw.getAmount();
				if(remainingBal < 0 && withdraw.getAmount() > 100000) {
					throw new Exception("Insufficient Balance");
				}else {
					if(remainingBal < 0) {
						accounts.setAccountBalance(0);
						double remainingODLimit = accounts.getOverDraft() + remainingBal;
						accounts.setOverDraft(remainingODLimit);
					}else{
						accounts.setAccountBalance(remainingBal);
					}
					accounts = transactionsRepository.save(accounts);

					transactionHistoryServiceImpl.saveTransactionHistory(accounts);

					accountMapper = new AccountMapper();
					return accountMapper.mapEntityToAccountDetails(accounts);
				}

			}

		}

		return accountDetails;
	}

}
