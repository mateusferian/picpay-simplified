package br.com.geradordedevs.picpaysimplified.services.impl;

import br.com.geradordedevs.picpaysimplified.clients.MockyClient;
import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import br.com.geradordedevs.picpaysimplified.repositories.UserRepository;
import br.com.geradordedevs.picpaysimplified.services.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MockyClient mockyClient;

    @Override
    public TransferResponseDTO transfer(TransferRequestDTO transferRequestDTO) {
        log.info("processing the transfer");

        transferringValue(transferRequestDTO);
        reducingTheBalanceOfThoseWhoTransferred(transferRequestDTO);
        return mockyClient.getMessage();
    }

    private void transferringValue(TransferRequestDTO transferRequestDTO){
        log.info("transferring value");

        UserEntity userEntity = userRepository.findByDocumentNumber(transferRequestDTO.getPayeeDocumentNumber());
        BigDecimal value = new BigDecimal(String.valueOf((userEntity.getValue()))).add(transferRequestDTO.getTransactionAmount());
        userEntity.setValue(value);

        userRepository.save(userEntity);
    }

    private void reducingTheBalanceOfThoseWhoTransferred(TransferRequestDTO transferRequestDTO){
        log.info("discounting the value of the user who made the transfer");

        UserEntity userEntity = userRepository.findByDocumentNumber(transferRequestDTO.getPayerDocumentNumber());
        BigDecimal value = new BigDecimal(String.valueOf((userEntity.getValue()))).subtract(transferRequestDTO.getTransactionAmount());
        userEntity.setValue(value);

        userRepository.save(userEntity);
    }
}
