package br.com.geradordedevs.picpaysimplified.services.impl;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import br.com.geradordedevs.picpaysimplified.exceptions.TransferException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.TransferEnum;
import br.com.geradordedevs.picpaysimplified.repositories.UserRepository;
import br.com.geradordedevs.picpaysimplified.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserEntity save(UserEntity userEntity) {
        log.info("registering a new user{}",userEntity);
        userEntity.setValue(new BigDecimal(0));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public void validateUserPassword( String password,Integer protractorDocumentNumber){

        UserEntity userEntity = userRepository.findByDocumentNumber(protractorDocumentNumber);
        if (userEntity == null ||
                !passwordEncoder.matches(password,userEntity.getPassword())){
            log.warn("document number or password is invalid");
            throw new TransferException(TransferEnum.INVALID_DOCUMENT_NUMBER_OR_PASSWORD);
        }
    }

    @Override
    public UserEntity findByDocumentNumber(Integer documentNumber){
        log.info("find user by document number");
        return userRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public UserEntity deposit(DepositRequestDTO depositRequestDTO){
        log.info("making deposit");
        UserEntity userEntity = userRepository.findByDocumentNumber(depositRequestDTO.getDocumentNumber());
        userEntity.setValue( new BigDecimal(String.valueOf(userEntity.getValue())).add(depositRequestDTO.getDepositAmount()));

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        log.info("find user by email");
        return userRepository.findByEmail(email);
    }
}
