package br.com.geradordedevs.picpaysimplified.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TypeOfUser {

    USER("user"),
    SHOPKEEPER("shopkeeper");

    private String status;
}
