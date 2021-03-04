package banktest.demo;

import banktest.demo.model.Account;
import banktest.demo.model.Client;
import banktest.demo.model.Transaction;
import banktest.demo.model.dto.request.AccountRequestDto;
import banktest.demo.repository.AccountRepository;
import banktest.demo.repository.ClientRepository;
import banktest.demo.repository.TransactionRepository;
import banktest.demo.service.mapper.AccountMapper;
import banktest.demo.service.mapper.ClientMapper;
import banktest.demo.service.mapper.TransactionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankApplicationTests {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void clientRepositoryTest() {
        Client expected = Client.builder().firstName("Andrei").lastName("Yazadzhi").id(1L).build();
        clientRepository.save(expected);
        Client actual = clientRepository.findById(1L).get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void accountRepositoryTest() {
        Client client = Client.builder().firstName("Andrei").lastName("Yazadzhi").build();
        clientRepository.save(client);
        Account expected = Account.builder()
                .client(client).balance(1000.00)
                .type("VISA").build();
        accountRepository.save(expected);
        Account actual = accountRepository.findAccountsByClient(client).get(0);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void transactionRepositoryTest() {
        Client client = Client.builder().firstName("Andrei").lastName("Yazadzhi").build();
        clientRepository.save(client);
        Account first = Account.builder()
                .client(client).balance(1000.00)
                .type("VISA").build();
        accountRepository.save(first);
        Client client2 = Client.builder().firstName("Bob").lastName("Allison").build();
        clientRepository.save(client2);
        Account second = Account.builder()
                .client(client2).balance(1000.00)
                .type("Simple/Card").build();
        accountRepository.save(second);
        Transaction expected = Transaction.builder().amount(100.00)
                .fromAccount(first).toAccount(second).reason("Salary").build();
        transactionRepository.save(expected);
        Transaction actual = transactionRepository.getAllByAccount(first).get(0);
        Assertions.assertEquals(expected, actual);
    }
}
