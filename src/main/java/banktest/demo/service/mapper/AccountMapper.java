package banktest.demo.service.mapper;

import banktest.demo.model.Account;
import banktest.demo.model.dto.request.AccountRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account fromDto(AccountRequestDto dto) {
        Account account = new Account();
        account.setBalance(dto.getBalance());
        account.setType(dto.getType());
        return account;
    }
}
