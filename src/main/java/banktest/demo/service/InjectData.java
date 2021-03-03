package banktest.demo.service;

import banktest.demo.model.Account;
import banktest.demo.model.Client;
import banktest.demo.model.Transaction;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InjectData {
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final ClientService clientService;

    @PostConstruct
    public void init() {
        Client firstClient = new Client();
        firstClient.setFirstName("Bob");
        firstClient.setLastName("Alison");
        clientService.save(firstClient);
        Account firstAccount = new Account();
        firstAccount.setBalance(1000.00);
        firstAccount.setType("Card");
        firstAccount.setClient(firstClient);
        accountService.save(firstAccount);
        Client secondClient = new Client();
        secondClient.setFirstName("Bob");
        secondClient.setLastName("Alison");
        clientService.save(secondClient);
        Account secondAccount = new Account();
        secondAccount.setBalance(1000.00);
        secondAccount.setType("Card");
        secondAccount.setClient(secondClient);
        accountService.save(secondAccount);
        Transaction transaction = new Transaction();
        transaction.setAmount(500.00);
        transaction.setFromAccount(firstAccount);
        transaction.setToAccount(secondAccount);
        transaction.setReason("Salary");
        transactionService.save(transaction);
    }
}
