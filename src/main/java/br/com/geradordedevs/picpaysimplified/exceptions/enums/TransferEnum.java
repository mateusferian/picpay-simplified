package br.com.geradordedevs.picpaysimplified.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TransferEnum {

    NEGATIVE_BALANCE("NEGATIVE_BALANCE","transfer cancelled, balance negative or equal to zero",404),

    INVALID_PAYER_OR_PASSWORD("INVALID_PAYER_OR_PASSWORD", "invalid payer or password",400),

    INCORRECT_USER_TYPE("INCORRECT_USER_TYPE", "this type of user cannot transfer",400);

    private String code;
    private String message;
    private Integer statusCode;
}
