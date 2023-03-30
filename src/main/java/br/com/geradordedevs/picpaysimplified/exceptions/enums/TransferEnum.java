package br.com.geradordedevs.picpaysimplified.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TransferEnum {

    INCORRECT_DOCUMENT_NUMBER("INCORRECT_DOCUMENT_NUMBER",
            "canceled transfer, incorrect document number of the recipient or user who performed the transfer",400),

    NEGATIVE_BALANCE("NEGATIVE_BALANCE","transfer cancelled, balance negative or equal to zero",404),

    INVALID_DOCUMENT_NUMBER_OR_PASSWORD("INVALID_DOCUMENT_NUMBER_OR_PASSWORD", "invalid document number or password",400),

    DOCUMENT_NUMBER_NOT_FOUND("DOCUMENT_NUMBER_NOT_FOUND", "document number not found",404),

    INCORRECT_USER_TYPE("INCORRECT_USER_TYPE", "this type of user cannot transfer",400);

    private String code;
    private String message;
    private Integer statusCode;
}
