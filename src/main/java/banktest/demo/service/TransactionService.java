package banktest.demo.service;

import banktest.demo.model.Account;
import banktest.demo.model.Transaction;
import java.util.List;

public interface TransactionService {
    void save(Transaction transaction);

    Transaction get(Long id);

    List<Transaction> getAllByAccount(Account account);
}
