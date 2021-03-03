package banktest.demo.model.dto.request;

import lombok.Data;

@Data
public class AccountRequestDto {
    private String type;
    private Double balance;
    private Long clientId;
}
