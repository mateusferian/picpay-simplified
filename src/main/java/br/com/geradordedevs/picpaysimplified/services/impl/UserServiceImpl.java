package br.com.geradordedevs.picpaysimplified.services.impl;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import br.com.geradordedevs.picpaysimplified.exceptions.UserException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.UserEnum;
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
    public void validateUserPassword( String password,Long id){

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserException(UserEnum.USER_NOT_FOUND));
        if (userEntity == null ||
                !passwordEncoder.matches(password,userEntity.getPassword())){
            log.warn("payer or password is invalid");
            throw new UserException(UserEnum.INVALID_USER_OR_PASSWORD);
        }
    }

    @Override
    public UserEntity findByDocumentNumber(String documentNumber){
        log.info("find user by document number");
        return userRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public UserEntity deposit(DepositRequestDTO depositRequestDTO){
        log.info("making deposit");
        UserEntity userEntity = findById(depositRequestDTO.getUser());
        userEntity.setValue( new BigDecimal(String.valueOf(userEntity.getValue())).add(depositRequestDTO.getDepositAmount()));

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        log.info("find user by email");
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findById(Long id) {
        log.info("getting user information {}", id);
        return userRepository.findById(id).orElseThrow(() -> new UserException(UserEnum.USER_NOT_FOUND));
    }
}
