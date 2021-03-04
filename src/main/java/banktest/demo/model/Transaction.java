package banktest.demo.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_account")
    @NotNull
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "to_account")
    @NotNull
    private Account toAccount;
    @Min(0)
    private Double amount;
    private String reason;
    @Column
    private LocalDateTime timestamp;
    @Column(name = "is_complete")
    private Boolean isComplete;
}
