package app.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.bank.entity.Accounts;
import app.bank.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>{

}
