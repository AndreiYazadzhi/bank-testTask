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
        Account fromAccount = accountService.get(transaction.getFromAccount().getNumber());
        Account toAccount = accountService.get(transaction.getToAccount().getNumber());
        Double amount = transaction.getAmount();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        if (fromAccount.getBalance() < amount) {
            transaction.setIsComplete(false);
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() - amount);
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
