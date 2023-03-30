package br.com.geradordedevs.picpaysimplified.services;

import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;

public interface TransferService {
    TransferResponseDTO transfer(TransferRequestDTO transferRequestDTO);
}
