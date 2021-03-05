package banktest.demo.service.impl;

import banktest.demo.model.Account;
import banktest.demo.model.Transaction;
import banktest.demo.repository.TransactionRepository;
import banktest.demo.service.AccountService;
import banktest.demo.service.TransactionService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    @Transactional
    public void save(Transaction transaction) {
        Account fromAccount = transaction.getFromAccount();
        Account toAccount = transaction.getToAccount();
        Double amount = transaction.getAmount();
        transaction.setTimestamp(LocalDateTime.now());
        if (fromAccount.getBalance() < amount) {
            transaction.setIsComplete(false);
            return;
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        transaction.setIsComplete(true);
        accountService.save(toAccount);
        accountService.save(fromAccount);
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction get(Long id) {
        return transactionRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Could not find transaction with id "
                + id + " in DB"));
    }

    @Override
    public List<Transaction> getAllByAccount(Account account) {
        return transactionRepository.getAllByAccount(account);
    }
}
