package br.com.geradordedevs.picpaysimplified.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {

    private Integer payerDocumentNumber;

    private String password;

    private BigDecimal transactionAmount;

    private Integer payeeDocumentNumber;
}
