package br.com.geradordedevs.picpaysimplified.exceptions;

import br.com.geradordedevs.picpaysimplified.exceptions.enums.TransferEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TransferException extends PicPaySimplifiedException{

    public TransferException(TransferEnum error){
        super(error.getMessage());
        this.error =  error;
    }
    private final TransferEnum error;
}
