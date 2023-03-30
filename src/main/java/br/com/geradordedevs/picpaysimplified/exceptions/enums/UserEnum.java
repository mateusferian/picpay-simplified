package br.com.geradordedevs.picpaysimplified.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserEnum{

    THIS_EMAIL_IS_ALREADY_BEING_USED("THIS_EMAIL_IS_ALREADY_BEING_USED", "This email is already being used",400),

    THIS_EMAIL_AND_CPF_IS_ALREADY_BEING_USED("THIS_EMAIL_AND_CPF_IS_ALREADY_BEING_USED", "This email and document number are already in use",400),

    THIS_CPF_IS_ALREADY_BEING_USED("THIS_CPF_IS_ALREADY_BEING_USED", "This document number is already being used",400),

    INVALID_USERNAME_OR_PASSWORD("INVALID_USERNAME_OR_PASSWORD", "Username or password is invalid",400);

    private String code;
    private String message;
    private Integer statusCode;
}
