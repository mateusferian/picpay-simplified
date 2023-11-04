package br.com.geradordedevs.picpaysimplified.facades.impl;

import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import br.com.geradordedevs.picpaysimplified.exceptions.TransferException;
import br.com.geradordedevs.picpaysimplified.services.TransferService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransferFacadeImplTest {

    @InjectMocks
    private TransferFacadeImpl transferFacade;

    @Mock
    private TransferService transferService;

    private final Long MOCK_PAYER = 1L;

    private final String MOCK_PASSWORD = "12345";

    private final BigDecimal MOCK_TRANSACTION_AMOUNT = new BigDecimal(10);

    private final BigDecimal MOCK_VALUE = new BigDecimal(5);

    private final BigDecimal MOCK_NEGATIVE_VALUE = new BigDecimal(-5);


    private final Long MOCK_PAYEE = 2L;

    private final String MOCK_MESSAGE = "Autorizado";

    private final Long MOCK_ID = 1l;

    private final String MOCK_NAME = "name";

    private final TypeOfUser MOCK_TYPE_OF_USER = TypeOfUser.USER;


    private final TypeOfUser MOCK_TYPE_OF_USER_SHOPKEEPER = TypeOfUser.SHOPKEEPER;

    private final String MOCK_EMAIL = "name@gmail.com";

    private final String MOCK_DOCUMENT_NUMBER = "12345678901";

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transferService.findByIdPayer(MOCK_ID)).thenReturn(returnObjectUserEntity());
        when(transferService.transfer(returnObjectTransferRequestDTO())).thenReturn(returnObjectTransferResponseDTO());
    }

    @Test
    public void transferMustReturnOk() throws Exception {
        checksIfTheValidatePayerPasswordMethodWasCalled();
        assertEquals(returnObjectTransferResponseDTO(),transferFacade.transfer(returnObjectTransferRequestDTO()));
    }

    @Test(expected = TransferException.class)
    public void transferMustReturnIncorrectUserType() throws Exception {
        when(transferService.findByIdPayer(MOCK_ID)).thenReturn(returnWithUserTypeShopkeeperObjectUserEntity());
        checksIfTheValidatePayerPasswordMethodWasCalled();
        transferFacade.transfer(returnObjectTransferRequestDTO());
    }

    @Test(expected = TransferException.class)
    public void transferMustReturnNegativeBalance() throws Exception {
        when(transferService.findByIdPayer(MOCK_ID)).thenReturn(returnWithNegativeValueObjectUserEntity());
        checksIfTheValidatePayerPasswordMethodWasCalled();
        transferFacade.transfer(returnObjectTransferRequestDTO());
    }

    private TransferRequestDTO returnObjectTransferRequestDTO() {
        return new TransferRequestDTO(MOCK_PAYER,MOCK_PASSWORD,MOCK_TRANSACTION_AMOUNT,MOCK_PAYEE);
    }

    private TransferResponseDTO returnObjectTransferResponseDTO() {
        return new TransferResponseDTO(MOCK_MESSAGE);
    }

    private UserEntity returnObjectUserEntity() {
        return new UserEntity(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,MOCK_PASSWORD,MOCK_VALUE);
    }

    private UserEntity returnWithUserTypeShopkeeperObjectUserEntity() {
        return new UserEntity(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER_SHOPKEEPER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,MOCK_PASSWORD,MOCK_VALUE);
    }

    private UserEntity returnWithNegativeValueObjectUserEntity() {
        return new UserEntity(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,MOCK_PASSWORD,MOCK_NEGATIVE_VALUE);
    }

    private void checksIfTheValidatePayerPasswordMethodWasCalled() {
        transferFacade.transfer(returnObjectTransferRequestDTO());
        verify(transferService,timeout(1)).validatePayerPassword(MOCK_PASSWORD,MOCK_ID);
    }
}

