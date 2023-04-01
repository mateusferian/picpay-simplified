package br.com.geradordedevs.picpaysimplified.controllers;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.DepositResponseDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.picpaysimplified.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaysimplified.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity(userFacade.save(userRequestDTO),HttpStatus.CREATED);
    }

    @GetMapping("/{documentNumber}")
    public ResponseEntity<UserResponseDTO> findByDocumentNumber(@PathVariable Integer documentNumber,
                                                                @RequestParam(required = false, value = "password") String password){
        return new ResponseEntity(userFacade.findByDocumentNumber(documentNumber,password),HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDTO> deposit(@Valid @RequestBody DepositRequestDTO depositRequestDTO){
        return new ResponseEntity(userFacade.deposit(depositRequestDTO),HttpStatus.OK);
    }
}
