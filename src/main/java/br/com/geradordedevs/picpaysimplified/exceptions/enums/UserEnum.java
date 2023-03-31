package br.com.geradordedevs.picpaysimplified.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserEnum{

    THIS_EMAIL_IS_ALREADY_BEING_USED("THIS_EMAIL_IS_ALREADY_BEING_USED", "This email is already being used",400),

    DOCUMENT_NUMBER_AND_EXISTING_EMAIL("DOCUMENT_NUMBER_AND_EXISTING_EMAIL", "This email and document number are already in use",400),

    DOCUMENT_NUMBER_EXISTING("DOCUMENT_NUMBER_EXISTING", "This document number is already being used",400),

    MANDATORY_PASSWORD("MANDATORY_PASSWORD", "Required password",401);

    private String code;
    private String message;
    private Integer statusCode;
}
