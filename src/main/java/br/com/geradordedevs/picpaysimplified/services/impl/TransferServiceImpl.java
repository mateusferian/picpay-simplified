package br.com.geradordedevs.picpaysimplified.services.impl;

import br.com.geradordedevs.picpaysimplified.clients.MockyClient;
import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import br.com.geradordedevs.picpaysimplified.exceptions.TransferException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.TransferEnum;
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

        UserEntity userEntity = userRepository.findById(transferRequestDTO.getPayee()).orElseThrow(() -> new TransferException(TransferEnum.PAYEE_NOT_FOUND));
        BigDecimal value = new BigDecimal(String.valueOf((userEntity.getValue()))).add(transferRequestDTO.getTransactionAmount());
        userEntity.setValue(value);

        userRepository.save(userEntity);
    }

    private void reducingTheBalanceOfThoseWhoTransferred(TransferRequestDTO transferRequestDTO){
        log.info("discounting the value of the user who made the transfer");

        UserEntity userEntity = findByIdPayer(transferRequestDTO.getPayer());
        BigDecimal value = new BigDecimal(String.valueOf((userEntity.getValue()))).subtract(transferRequestDTO.getTransactionAmount());
        userEntity.setValue(value);

        userRepository.save(userEntity);
    }

    @Override
    public void validatePayerPassword( String password,Long id) {
        log.info("validating user password");
        UserEntity userEntity = findByIdPayer(id);
        if (userEntity == null ||
                !passwordEncoder.matches(password, userEntity.getPassword())) {
            log.warn("payer or password is invalid");
            throw new TransferException(TransferEnum.INVALID_PAYER_OR_PASSWORD);
        }
    }

    @Override
    public UserEntity findByIdPayer(Long id) {
        log.info("getting user information {}", id);
        return userRepository.findById(id).orElseThrow(() -> new TransferException(TransferEnum.PAYER_NOT_FOUND));
    }
}
