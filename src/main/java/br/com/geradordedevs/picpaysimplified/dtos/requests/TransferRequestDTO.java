package br.com.geradordedevs.picpaysimplified.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {

    @NotNull(message = "{null.field}")
    private Long payer;

    @NotBlank(message = "{white.field}")
    private String password;

    @NotNull(message = "{null.field}")
    @Min(value = 1, message = "{below.the.minimum}")
    @Max(value = 100000, message = "{above.the.maximum}")
    private BigDecimal transactionAmount;

    @NotNull(message = "{null.field}")
    private Long payee;
}
