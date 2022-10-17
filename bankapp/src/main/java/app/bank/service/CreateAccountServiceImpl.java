package app.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.bank.domain.AccountDetails;
import app.bank.entity.Accounts;
import app.bank.mapper.AccountMapper;
import app.bank.repository.CreateAccountRepository;

@Service
public class CreateAccountServiceImpl {
	
	@Autowired
	private CreateAccountRepository createAccountRepository;
	
	public Accounts createAccount(AccountDetails accountDetails) {
		
		AccountMapper acctmapper = new AccountMapper();
		Accounts accounts = acctmapper.mapAccountDomainToEntity(accountDetails);
		
		Accounts accountsResp = createAccountRepository.save(accounts);
		System.out.println("End of transaction in serviceimpl - account created");
		return accountsResp;
	}

}
