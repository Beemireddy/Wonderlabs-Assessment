package app.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("app.bank.repository") 
@EntityScan("app.bank.entity")
@SpringBootApplication
public class BankappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankappApplication.class, args);
	}

}
