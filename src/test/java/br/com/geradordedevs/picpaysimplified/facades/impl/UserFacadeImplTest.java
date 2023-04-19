package br.com.geradordedevs.picpaysimplified.facades.impl;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import br.com.geradordedevs.picpaysimplified.exceptions.UserException;
import br.com.geradordedevs.picpaysimplified.mappers.UserMapper;
import br.com.geradordedevs.picpaysimplified.services.UserService;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserFacadeImplTest {

    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserService userService;

    private final BigDecimal MOCK_DEPOSIT_AMOUNT = new BigDecimal(10);

    private final String MOCK_DOCUMENT_NUMBER = "1234567890";

    private final String MOCK_DOCUMENT_NUMBER_NOT_FORMATTED= "12.345-67/89 0";

    private final Long MOCK_ID = 1l;

    private final String MOCK_PASSWORD = "12345";


    private final String MOCK_NAME = "name";

    private final TypeOfUser MOCK_TYPE_OF_USER = TypeOfUser.USER;

    private final String MOCK_EMAIL = "name@gmail.com";

    private final BigDecimal MOCK_VALUE = new BigDecimal(5);

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(userService.deposit(returnObjectDepositRequestDTO())).thenReturn(returnObjectUserEntity());
        when(userService.findById(MOCK_ID)).thenReturn(returnObjectUserEntity());
        when(userService.findByEmail(MOCK_EMAIL)).thenReturn(null);
        when(userService.findByDocumentNumber(MOCK_DOCUMENT_NUMBER)).thenReturn(null);
        when(userService.save(returnObjectUserEntity())).thenReturn(returnObjectUserEntity());

        when(mapper.toEntity(returnWithFormattedDocumentNumberObjectUserRequestDTO())).thenReturn(returnObjectUserEntity());
        when(mapper.toDto(returnObjectUserEntity())).thenReturn(returnObjectUserResponseDTO());
    }

    @Test
    public void saveMustReturnOk() throws Exception {
        assertEquals(returnObjectUserResponseDTO(),userFacade.save(returnObjectUserRequestDTO()));
    }

    @Test(expected = UserException.class)
    public void saveMustReturnDocumentNumberAndExistingEmail() throws Exception {
        when(userService.findByEmail(MOCK_EMAIL)).thenReturn(returnObjectUserEntity());
        when(userService.findByDocumentNumber(MOCK_DOCUMENT_NUMBER)).thenReturn(returnObjectUserEntity());
        userFacade.save(returnObjectUserRequestDTO());
    }

    @Test(expected = UserException.class)
    public void saveMustReturnThisEmailIsAlreadyBeingUsed() throws Exception {
        when(userService.findByEmail(MOCK_EMAIL)).thenReturn(returnObjectUserEntity());
        userFacade.save(returnObjectUserRequestDTO());
    }

    @Test(expected = UserException.class)
    public void saveMustReturnDocumentNumberExisting() throws Exception {
        when(userService.findByDocumentNumber(MOCK_DOCUMENT_NUMBER)).thenReturn(returnObjectUserEntity());
        userFacade.save(returnObjectUserRequestDTO());
    }

    @Test
    public void findByIdMustReturnOk() throws Exception {
        assertEquals(returnObjectUserResponseDTO(),userFacade.findById(MOCK_ID,MOCK_PASSWORD));
        checksIfTheValidateUserPasswordMethodWasCalled();
    }

    @Test(expected = UserException.class)
    public void findByIdMustReturnUserException() throws Exception {
        assertEquals(returnObjectUserResponseDTO(),userFacade.findById(MOCK_ID,null));
        checksIfTheValidateUserPasswordMethodWasCalled();
    }

    @Test
    public void depositMustReturnOk() throws Exception {
        assertEquals(returnObjectUserResponseDTO(),userFacade.deposit(returnObjectDepositRequestDTO()));
        checksIfTheValidateUserPasswordMethodWasCalled();
    }

    private DepositRequestDTO returnObjectDepositRequestDTO() {
        return new DepositRequestDTO(MOCK_ID,MOCK_PASSWORD,MOCK_DEPOSIT_AMOUNT);
    }

    private UserResponseDTO returnObjectUserResponseDTO() {
        return new UserResponseDTO(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_EMAIL,MOCK_VALUE);
    }

    private UserRequestDTO returnWithFormattedDocumentNumberObjectUserRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserRequestDTO returnObjectUserRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER_NOT_FORMATTED,
                MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserEntity returnObjectUserEntity() {
        return new UserEntity(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,MOCK_PASSWORD,MOCK_VALUE);
    }

    private void checksIfTheValidateUserPasswordMethodWasCalled() {
        verify(userService,timeout(1)).validateUserPassword(MOCK_PASSWORD,MOCK_ID);
    }
}
