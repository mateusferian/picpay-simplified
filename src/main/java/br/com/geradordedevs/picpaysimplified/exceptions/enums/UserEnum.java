package br.com.geradordedevs.picpaysimplified.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserEnum{

    THIS_EMAIL_IS_ALREADY_BEING_USED("THIS_EMAIL_IS_ALREADY_BEING_USED", "Este email já esta sendo usado",400),

    THIS_EMAIL_AND_CPF_IS_ALREADY_BEING_USED("THIS_EMAIL_AND_CPF_IS_ALREADY_BEING_USED", "Este email e numero de documento já estão sendo usados",400),

    THIS_CPF_IS_ALREADY_BEING_USED("THIS_CPF_IS_ALREADY_BEING_USED", "Este numero de documento já esta sendo usado",400),

    INVALID_USERNAME_OR_PASSWORD("INVALID_USERNAME_OR_PASSWORD", "Usuario ou senha invalidos",400);

    private String code;
    private String message;
    private Integer statusCode;
}
