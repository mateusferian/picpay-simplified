package br.com.geradordedevs.picpaysimplified.facades;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.UserResponseDTO;

public interface UserFacade {
    UserResponseDTO save(UserRequestDTO userRequestDTO);
    UserResponseDTO findById(Long id,String password);
    UserResponseDTO deposit(DepositRequestDTO depositRequestDTO);
}
