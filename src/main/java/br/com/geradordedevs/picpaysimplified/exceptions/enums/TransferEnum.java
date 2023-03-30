package br.com.geradordedevs.picpaysimplified.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TransferEnum {

    INCORRECT_DOCUMENT_NUMBER("INCORRECT_DOCUMENT_NUMBER",
            "trasferencia cancelada,numero do documento do destinatario ou do usuario que efetuou a trasferencia incorreto",400),

    NEGATIVE_BALANCE("NEGATIVE_BALANCE","trasferencia cancelada,saldo negativo ou igual a zero",404),

    INVALID_DOCUMENT_NUMBER_OR_PASSWORD("INVALID_DOCUMENT_NUMBER_OR_PASSWORD", "trasferencia cancelada," +
            "numero de documento ou senha invalidos",400),

    DOCUMENT_NUMBER_NOT_FOUND("DOCUMENT_NUMBER_NOT_FOUND", "numero do documento não encontrado",404),

    INCORRECT_USER_TYPE("INCORRECT_USER_TYPE", "tipo de usuario inpossibilitado de efetuar trasferencia",400);

    private String code;
    private String message;
    private Integer statusCode;
}
