package br.com.geradordedevs.picpaysimplified.services;

import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;

public interface TransferService {
    TransferResponseDTO transfer(TransferRequestDTO transferRequestDTO);
    void validatePayerPassword( String password,Long id);
    UserEntity findByIdPayer(Long id);
}
