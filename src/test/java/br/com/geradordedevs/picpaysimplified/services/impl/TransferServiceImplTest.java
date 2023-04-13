package br.com.geradordedevs.picpaysimplified.services.impl;

import br.com.geradordedevs.picpaysimplified.clients.MockyClient;
import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.TransferResponseDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import br.com.geradordedevs.picpaysimplified.exceptions.TransferException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.TransferEnum;
import br.com.geradordedevs.picpaysimplified.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransferServiceImplTest {

    @InjectMocks
    private TransferServiceImpl transferService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private MockyClient mockyClient;

    private final String MOCK_DOCUMENT_NUMBER = "1234567890";

    private final Long MOCK_ID = 1l;

    private final String MOCK_PASSWORD = "12345";

    private final String MOCK_NAME = "name";

    private final TypeOfUser MOCK_TYPE_OF_USER = TypeOfUser.USER;


    private final String MOCK_EMAIL = "name@gmail.com";

    private final BigDecimal MOCK_VALUE = new BigDecimal(20);

    private final Long MOCK_PAYER = 1L;

    private final BigDecimal MOCK_TRANSACTION_AMOUNT = new BigDecimal(10);

    private final Long MOCK_PAYEE = 2L;


    private final String MOCK_MESSAGE = "Autorizado";

    private final boolean MOCK_TRUE = true;

    private final boolean MOCK_FALSE = false;

    private final String ENCRYPTED_PASSWORD = "!@#QWEewq321";

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(returnObjectUserEntity()));
        when(userRepository.findById(MOCK_PAYEE)).thenReturn(Optional.of(returnFromPayeeTheObjectUserEntity()));

        when(mockyClient.getMessage()).thenReturn(returnObjectTransferResponseDTO());
        when(passwordEncoder.matches(MOCK_PASSWORD,ENCRYPTED_PASSWORD)).thenReturn(MOCK_TRUE);
    }

    @Test
    public void transferMustReturnOk() throws Exception {
        assertEquals(returnObjectTransferResponseDTO(),transferService.transfer(returnObjectTransferRequestDTO()));
        verify(userRepository,timeout(1)).save(returnWithtransactionBeingAddedToThePayeeValueTheObjectUserEntity());
        verify(userRepository,timeout(1)).save(returnWithTheTransactionBeingDiscountedToThePayerValueTheObjectUserEntity());
    }

    @Test(expected = TransferException.class)
    public void transferMustReturnOkTransferException() throws Exception {
        when(userRepository.findById(MOCK_PAYEE)).thenThrow(new TransferException(TransferEnum.PAYEE_NOT_FOUND));
        transferService.transfer(returnObjectTransferRequestDTO());
    }

    @Test
    public void validatePayerPasswordMustReturnOk() throws Exception {
        transferService.validatePayerPassword(MOCK_PASSWORD,MOCK_ID);
    }

    @Test(expected = TransferException.class)
    public void validatePayerPasswordMustReturnTransferException() throws Exception {
        when(passwordEncoder.matches(MOCK_PASSWORD,ENCRYPTED_PASSWORD)).thenReturn(MOCK_FALSE);
        transferService.validatePayerPassword(MOCK_PASSWORD,MOCK_ID);
    }

    @Test
    public void findByIdPayerMustReturnOk() throws Exception {
        assertEquals(returnObjectUserEntity(),transferService.findByIdPayer(MOCK_ID));
    }

    @Test(expected = TransferException.class)
    public void findByIdPayerMustReturnTransferException() throws Exception {
        when(userRepository.findById(MOCK_ID)).thenThrow(new TransferException(TransferEnum.PAYER_NOT_FOUND));
        transferService.findByIdPayer(MOCK_ID);
    }

    private TransferRequestDTO returnObjectTransferRequestDTO() {
        return new TransferRequestDTO(MOCK_PAYER,MOCK_PASSWORD,MOCK_TRANSACTION_AMOUNT,MOCK_PAYEE);
    }

    private TransferResponseDTO returnObjectTransferResponseDTO() {
        return new TransferResponseDTO(MOCK_MESSAGE);
    }

    private UserEntity returnObjectUserEntity() {
        return new UserEntity(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,ENCRYPTED_PASSWORD,MOCK_VALUE);
    }

    private UserEntity returnFromPayeeTheObjectUserEntity() {
        return new UserEntity(MOCK_PAYEE,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,ENCRYPTED_PASSWORD,MOCK_VALUE);
    }

    private UserEntity returnWithTheTransactionBeingDiscountedToThePayerValueTheObjectUserEntity() {
        return new UserEntity(MOCK_PAYER,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,ENCRYPTED_PASSWORD,MOCK_VALUE.subtract(MOCK_TRANSACTION_AMOUNT));
    }

    private UserEntity returnWithtransactionBeingAddedToThePayeeValueTheObjectUserEntity() {
        return new UserEntity(MOCK_PAYEE,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,ENCRYPTED_PASSWORD,MOCK_VALUE.add(MOCK_TRANSACTION_AMOUNT));
    }
}
