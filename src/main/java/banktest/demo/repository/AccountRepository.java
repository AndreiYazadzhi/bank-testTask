package banktest.demo.repository;

import banktest.demo.model.Account;
import banktest.demo.model.Client;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAccountsByClient(Client client);
}
