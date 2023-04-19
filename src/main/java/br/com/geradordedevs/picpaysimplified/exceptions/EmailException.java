package br.com.geradordedevs.picpaysimplified.exceptions;

import br.com.geradordedevs.picpaysimplified.exceptions.enums.EmailEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class EmailException extends RuntimeException{

    public EmailException(EmailEnum error){
        super(error.getMessage());
        this.error =  error;
    }
    private final EmailEnum error;
}
