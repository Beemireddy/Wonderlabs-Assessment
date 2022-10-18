package app.bank.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.bank.domain.AccountDetails;
import app.bank.entity.Accounts;
import app.bank.service.CreateAccountServiceImpl;
import app.bank.util.AccountType;

@RestController
@RequestMapping("api")
public class CreateAccountController {
	
	@Autowired
	private CreateAccountServiceImpl createAccountServiceImpl;
	private AccountDetails accountDetailsResp;
	
	@PostMapping("/account/new")	
	public AccountDetails createAccounts(@Validated @RequestBody AccountDetails accountDetails) {
		String accountType = accountDetails.getAccountType();
		double accountBalance = accountDetails.getAccountBalance();
		accountDetails.setCreatedTime(LocalDateTime.now());
		System.out.println(accountDetails);
		accountDetailsResp = new AccountDetails();
		if(accountType.equalsIgnoreCase(AccountType.SAVINGS.toString())) {
				if(accountBalance >= 1000) {					
					accountDetailsResp = createAccountServiceImpl.createAccount(accountDetails);
				} else {
					accountDetailsResp.setStatusMesg("Account cannot be created as minimum bal is R1000 required");
				}
			accountDetailsResp.setOverDraft(0);
		}
		if(accountType.equalsIgnoreCase(AccountType.CURRENT.toString())) {
			accountDetailsResp = createAccountServiceImpl.createAccount(accountDetails);
			accountDetailsResp.setOverDraft(100000);
		}
				
		return accountDetailsResp;
	}
	
}
