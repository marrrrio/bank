package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransferDto {

    @NonNull
    private Long fromId;
    @NotNull
    private Long toId;
    @NonNull
    private BigDecimal sumOfMoney;
}