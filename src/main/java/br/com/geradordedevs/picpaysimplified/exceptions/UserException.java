package br.com.geradordedevs.picpaysimplified.exceptions;

import br.com.geradordedevs.picpaysimplified.exceptions.enums.UserEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserException extends PicPaySimplifiedException{

    public UserException(UserEnum error){
        super(error.getMessage());
        this.error =  error;
    }

    private final UserEnum error;
}
