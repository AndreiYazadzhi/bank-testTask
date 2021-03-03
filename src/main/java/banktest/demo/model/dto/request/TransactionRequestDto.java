package banktest.demo.model.dto.request;

import lombok.Data;

@Data
public class TransactionRequestDto {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private String reason;
}
