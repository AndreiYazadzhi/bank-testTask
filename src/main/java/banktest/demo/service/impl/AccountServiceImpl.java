package banktest.demo.service.impl;

import banktest.demo.exception.EntityNotFoundException;
import banktest.demo.model.Account;
import banktest.demo.repository.AccountRepository;
import banktest.demo.repository.ClientRepository;
import banktest.demo.service.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account get(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                "Could not find account with id " + id + " in DB"));
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<Account> findByClientId(Long id) {
        return accountRepository.findAccountsByClient(clientRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Could not find transactions with client id "
                + id + " in DB")));
    }
}
