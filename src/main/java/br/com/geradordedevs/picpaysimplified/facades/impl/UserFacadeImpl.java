package br.com.geradordedevs.picpaysimplified.facades.impl;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.picpaysimplified.exceptions.TransferException;
import br.com.geradordedevs.picpaysimplified.exceptions.UserException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.TransferEnum;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.UserEnum;
import br.com.geradordedevs.picpaysimplified.facades.UserFacade;
import br.com.geradordedevs.picpaysimplified.mappers.UserMapper;
import br.com.geradordedevs.picpaysimplified.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {

        if (userService.findByEmail(userRequestDTO.getEmail()) != null && userService.findByDocumentNumber(userRequestDTO.getDocumentNumber()) != null) {
            throw new UserException(UserEnum.THIS_EMAIL_AND_CPF_IS_ALREADY_BEING_USED);

        } else if (userService.findByEmail(userRequestDTO.getEmail()) != null) {
            throw new UserException(UserEnum.THIS_EMAIL_IS_ALREADY_BEING_USED);

        } else if (userService.findByDocumentNumber(userRequestDTO.getDocumentNumber()) != null) {
            throw new UserException(UserEnum.THIS_CPF_IS_ALREADY_BEING_USED);

        } else {
            return mapper.toDto(userService.save(mapper.toEntity(userRequestDTO)));
        }
    }

    @Override
    public UserResponseDTO findByDocumentNumber(Integer documentNumber,String password) {

        if (password == null){
            throw new UserException(UserEnum.MANDATORY_PASSWORD);
        }

        if (userService.findByDocumentNumber(documentNumber) == null){
            throw new UserException(UserEnum.THIS_EMAIL_IS_ALREADY_BEING_USED);
        }
        userService.validateUserPassword(password,documentNumber);
        return mapper.toDto(userService.findByDocumentNumber(documentNumber));
    }

    @Override
    public UserResponseDTO deposit(DepositRequestDTO depositRequestDTO) {

        if (userService.findByDocumentNumber(depositRequestDTO.getDocumentNumber()) == null){
            throw new TransferException(TransferEnum.DOCUMENT_NUMBER_NOT_FOUND);
        }

        userService.validateUserPassword(depositRequestDTO.getPassword(),depositRequestDTO.getDocumentNumber());
        return mapper.toDto(userService.deposit(depositRequestDTO));
    }
}
