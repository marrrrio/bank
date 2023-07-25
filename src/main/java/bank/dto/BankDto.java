package bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDto {

    private Long clientId;

    @NotNull
    private Integer clientAge;

    @NotNull
    private BigDecimal clientMoney;

    @NotBlank
    private String clientName;
}