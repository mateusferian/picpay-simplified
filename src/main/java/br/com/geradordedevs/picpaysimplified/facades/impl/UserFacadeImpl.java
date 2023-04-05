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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {

        if (userService.findByEmail(userRequestDTO.getEmail()) != null && userService.findByDocumentNumber(userRequestDTO.getDocumentNumber()) != null) {
            throw new UserException(UserEnum.DOCUMENT_NUMBER_AND_EXISTING_EMAIL);

        } else if (userService.findByEmail(userRequestDTO.getEmail()) != null) {
            throw new UserException(UserEnum.THIS_EMAIL_IS_ALREADY_BEING_USED);

        } else if (userService.findByDocumentNumber(userRequestDTO.getDocumentNumber()) != null) {
            throw new UserException(UserEnum.DOCUMENT_NUMBER_EXISTING);

        } else {
            return mapper.toDto(userService.save(mapper.toEntity(userRequestDTO)));
        }
    }

    @Override
    public UserResponseDTO findById(Long id,String password) {

        if (password == null){
            throw new UserException(UserEnum.MANDATORY_PASSWORD);
        }

        userService.validateUserPassword(password,id);
        return mapper.toDto(userService.findById(id));
    }

    @Override
    public UserResponseDTO deposit(DepositRequestDTO depositRequestDTO) {

        userService.validateUserPassword(depositRequestDTO.getPassword(),depositRequestDTO.getPayer());
        return mapper.toDto(userService.deposit(depositRequestDTO));
    }
}
