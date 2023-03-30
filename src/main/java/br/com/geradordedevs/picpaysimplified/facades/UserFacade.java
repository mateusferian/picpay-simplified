package br.com.geradordedevs.picpaysimplified.facades;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.picpaysimplified.dtos.requests.UserRequestDTO;

import java.util.List;

public interface UserFacade {
    UserResponseDTO save(UserRequestDTO userRequestDTO);
    UserResponseDTO findByDocumentNumber(Integer documentNumber,String password);
    UserResponseDTO deposit(DepositRequestDTO depositRequestDTO);
}
