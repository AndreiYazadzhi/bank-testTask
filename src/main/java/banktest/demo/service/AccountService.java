package banktest.demo.service;

import banktest.demo.model.Account;
import java.util.List;

public interface AccountService {
    void save(Account account);

    Account get(Long id);

    void delete(Long id);

    List<Account> findByClientId(Long id);
}
