package banktest.demo.repository;

import banktest.demo.model.Account;
import banktest.demo.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Query(value = "from Transaction t where t.fromAccount = ?1 or t.toAccount = ?1")
    List<Transaction> getAllByAccount(Account account);
}
