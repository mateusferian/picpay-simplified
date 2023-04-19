package br.com.geradordedevs.picpaysimplified.facades.impl;

import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;
import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import br.com.geradordedevs.picpaysimplified.exceptions.TransferException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.TransferEnum;
import br.com.geradordedevs.picpaysimplified.facades.TransferFacade;
import br.com.geradordedevs.picpaysimplified.services.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransferFacadeImpl implements TransferFacade {

    @Autowired
    private TransferService transferService;

    @Override
    public TransferResponseDTO transfer(TransferRequestDTO transferRequestDTO) {

        if (transferService.findByIdPayer(transferRequestDTO.getPayer()).getTypeOfUser() == TypeOfUser.SHOPKEEPER){

            throw new TransferException(TransferEnum.INCORRECT_USER_TYPE);

        } else if (transferService.findByIdPayer(transferRequestDTO.getPayer()).getValue().doubleValue() <= 0) {

            throw new TransferException(TransferEnum.NEGATIVE_BALANCE);

        } else {
            transferService.validatePayerPassword(transferRequestDTO.getPassword(),transferRequestDTO.getPayer());
            return transferService.transfer(transferRequestDTO);
        }
    }
}
