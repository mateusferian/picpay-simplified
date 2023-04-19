package br.com.geradordedevs.picpaysimplified.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequestDTO {

    @NotNull(message = "{null.field}")
    private Long user;

    @NotBlank(message = "{white.field}")
    private String password;

    @NotNull(message = "{null.field}")
    @Min(value = 1, message = "{below.the.minimum}")
    @Max(value = 100000, message = "{above.the.maximum}")
    private BigDecimal depositAmount;
}
