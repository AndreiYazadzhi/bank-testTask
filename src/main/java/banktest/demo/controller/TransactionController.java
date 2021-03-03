package banktest.demo.controller;

import banktest.demo.model.Transaction;
import banktest.demo.model.dto.request.TransactionRequestDto;
import banktest.demo.service.AccountService;
import banktest.demo.service.TransactionService;
import banktest.demo.service.mapper.TransactionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final TransactionMapper mapper;
    private final EntityManager entityManager;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Transaction> create(@RequestBody TransactionRequestDto dto) {
        Transaction transaction = mapper.fromDto(dto);
        transactionService.save(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PostMapping(value = "/list",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Transaction>> createList(
            @RequestBody List<TransactionRequestDto> dtos) {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction;
        for (TransactionRequestDto dto: dtos) {
            transaction = mapper.fromDto(dto);
            transactionService.save(transaction);
            transactions.add(transaction);
        }
        return new ResponseEntity<>(transactions, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Transaction> get(@PathVariable Long id) {
        Transaction transaction = transactionService.get(id);
        transactionService.save(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping(value = "/by-account/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Transaction> getByAccountId(@PathVariable Long id) {
        return transactionService.getAllByAccount(accountService.get(id));
    }

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Transaction> getJournal(
            @RequestBody Map<String, String> params) {
        CriteriaBuilder criteriaBuilder = entityManager
                .getCriteriaBuilder();
        CriteriaQuery<Transaction> transactionQuery = criteriaBuilder
                .createQuery(Transaction.class);
        Root<Transaction> transactionRoot = transactionQuery.from(Transaction.class);
        List<Predicate> predicateList = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            predicateList.add(transactionRoot.get(entry.getKey()).in(entry.getValue()));
        }
        transactionQuery.select(transactionRoot).where(predicateList.toArray(new Predicate[0]));
        return entityManager.createQuery(transactionQuery).getResultList();
    }
}
