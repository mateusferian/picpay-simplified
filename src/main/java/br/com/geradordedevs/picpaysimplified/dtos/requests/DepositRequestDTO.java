package br.com.geradordedevs.picpaysimplified.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequestDTO {

    private Integer documentNumber;

    private String password;

    private BigDecimal depositAmount;
}
