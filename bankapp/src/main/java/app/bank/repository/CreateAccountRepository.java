package app.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.bank.entity.Accounts;

public interface CreateAccountRepository extends JpaRepository<Accounts, Long>{

}
