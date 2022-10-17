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

@RestController
@RequestMapping("api")
public class CreateAccountController {
	
	@Autowired
	private CreateAccountServiceImpl createAccountServiceImpl;
	
	@PostMapping("/account/new")
	
	public Accounts createAccounts(@Validated @RequestBody AccountDetails accountDetails) {
		String accountType = accountDetails.getAccountType();
		double accountBalance = accountDetails.getAccountBalance();
		accountDetails.setCreatedTime(LocalDateTime.now());
		System.out.println(accountDetails);
			
		return createAccountServiceImpl.createAccount(accountDetails);
		
	}
	
}
