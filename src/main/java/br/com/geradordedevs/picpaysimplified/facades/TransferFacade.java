package br.com.geradordedevs.picpaysimplified.facades;

import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;

public interface TransferFacade {

    TransferResponseDTO transfer(TransferRequestDTO transferRequestDTO);
}
