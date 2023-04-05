package br.com.geradordedevs.picpaysimplified.services;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;

public interface UserService {
    UserEntity save(UserEntity userEntity);
    void validateUserPassword( String getPassword, Long id);
    UserEntity findByDocumentNumber(Integer documentNumber);
    UserEntity deposit(DepositRequestDTO depositRequestDTO);
    UserEntity findByEmail(String email);
    UserEntity findById(Long id);
}
