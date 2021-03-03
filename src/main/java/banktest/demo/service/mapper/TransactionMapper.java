package banktest.demo.service.mapper;

import banktest.demo.model.Transaction;
import banktest.demo.model.dto.request.TransactionRequestDto;
import banktest.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TransactionMapper {
    private final AccountService accountService;

    public Transaction fromDto(TransactionRequestDto dto) {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(accountService.get(dto.getFromAccountId()));
        transaction.setToAccount(accountService.get(dto.getToAccountId()));
        transaction.setAmount(dto.getAmount());
        transaction.setReason(dto.getReason());
        return transaction;
    }
}
