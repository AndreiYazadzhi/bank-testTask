package banktest.demo.model.dto.request;

import banktest.demo.model.Account;
import java.util.List;
import lombok.Data;

@Data
public class ClientRequestDto {
    private String firstName;
    private String lastName;
    private List<Account> accounts;
}
