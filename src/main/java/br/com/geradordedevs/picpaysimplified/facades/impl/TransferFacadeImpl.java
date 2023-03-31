package br.com.geradordedevs.picpaysimplified.facades.impl;

import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;
import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import br.com.geradordedevs.picpaysimplified.exceptions.TransferException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.TransferEnum;
import br.com.geradordedevs.picpaysimplified.facades.TransferFacade;
import br.com.geradordedevs.picpaysimplified.services.TransferService;
import br.com.geradordedevs.picpaysimplified.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransferFacadeImpl implements TransferFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    @Override
    public TransferResponseDTO transfer(TransferRequestDTO transferRequestDTO) {

        if (userService.findByDocumentNumber(transferRequestDTO.getPayeeDocumentNumber()) == null ||
                userService.findByDocumentNumber(transferRequestDTO.getPayerDocumentNumber()) == null) {

            throw new TransferException(TransferEnum.INCORRECT_DOCUMENT_NUMBER);

        } else if (userService.findByDocumentNumber(transferRequestDTO.getPayerDocumentNumber()).getTypeOfUser() == TypeOfUser.SHOPKEEPER){

            throw new TransferException(TransferEnum.INCORRECT_USER_TYPE);

        } else if (userService.findByDocumentNumber(transferRequestDTO.getPayerDocumentNumber()).getValue().doubleValue() <= 0) {

            throw new TransferException(TransferEnum.NEGATIVE_BALANCE);

        } else {
            userService.validateUserPassword(transferRequestDTO.getPassword(),transferRequestDTO.getPayerDocumentNumber());
            return transferService.transfer(transferRequestDTO);
        }
    }
}
