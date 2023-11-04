package br.com.geradordedevs.picpaysimplified.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EmailEnum {

    EMAIL_ERROR("EMAIL_ERROR","there was an error sending the email please try again",500);

    private String code;
    private String message;
    private Integer statusCode;
}
