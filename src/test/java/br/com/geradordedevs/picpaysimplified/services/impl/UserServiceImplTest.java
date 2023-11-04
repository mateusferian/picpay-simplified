package br.com.geradordedevs.picpaysimplified.services.impl;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import br.com.geradordedevs.picpaysimplified.exceptions.UserException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.UserEnum;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private final BigDecimal MOCK_DEPOSIT_AMOUNT = new BigDecimal(10);

    private final String MOCK_DOCUMENT_NUMBER = "1234567890";

    private final Long MOCK_ID = 1l;

    private final String MOCK_PASSWORD = "12345";

    private final String MOCK_NAME = "name";


    private final TypeOfUser MOCK_TYPE_OF_USER = TypeOfUser.USER;

    private final String MOCK_EMAIL = "name@gmail.com";

    private final BigDecimal MOCK_VALUE = new BigDecimal(0);

    private final boolean MOCK_TRUE = true;

    private final boolean MOCK_FALSE = false;


    private final String ENCRYPTED_PASSWORD = "!@#QWEewq321";

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(userRepository.save(returnObjectUserEntity())).thenReturn(returnObjectUserEntity());
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(returnObjectUserEntity()));
        when(userRepository.findByDocumentNumber(MOCK_DOCUMENT_NUMBER)).thenReturn((returnObjectUserEntity()));
        when(userRepository.save(returnWithDepositAmountAddedTheObjectUserEntity())).thenReturn(returnWithDepositAmountAddedTheObjectUserEntity());
        when(userRepository.findByEmail(MOCK_EMAIL)).thenReturn((returnObjectUserEntity()));

        when(passwordEncoder.encode(MOCK_PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
        when(passwordEncoder.matches(MOCK_PASSWORD,ENCRYPTED_PASSWORD)).thenReturn(MOCK_TRUE);
    }

    @Test
    public void saveMustReturnOk() throws Exception{
        assertEquals(returnObjectUserEntity(),userService.save(returnWithNullValueTheObjectUserEntity()));
    }

    @Test
    public void validateUserPasswordMustReturnOk() throws Exception{
        userService.validateUserPassword(MOCK_PASSWORD,MOCK_ID);
    }

    @Test(expected = UserException.class)
    public void validateUserPasswordMustReturnUserException() throws Exception{
        when(passwordEncoder.matches(MOCK_PASSWORD,ENCRYPTED_PASSWORD)).thenReturn(MOCK_FALSE);
        userService.validateUserPassword(MOCK_PASSWORD,MOCK_ID);
    }

    @Test
    public void findByDocumentNumberMustReturnOk() throws Exception{
        assertEquals(returnObjectUserEntity(),userService.findByDocumentNumber(MOCK_DOCUMENT_NUMBER));
    }

    @Test
    public void depositMustReturnOk() throws Exception{
        assertEquals(returnWithDepositAmountAddedTheObjectUserEntity(),userService.deposit(returnObjectDepositRequestDTO()));
    }

    @Test
    public void findByEmailMustReturnOk() throws Exception{
        assertEquals(returnObjectUserEntity(),userService.findByEmail(MOCK_EMAIL));
    }

    @Test
    public void findByIdMustReturnOk() throws Exception{
        assertEquals(returnObjectUserEntity(),userService.findById(MOCK_ID));
    }

    @Test(expected = UserException.class)
    public void findByIdMustReturnUserException() throws Exception{
        when(userRepository.findById(MOCK_ID)).thenThrow(new UserException(UserEnum.USER_NOT_FOUND));
        assertEquals(returnObjectUserEntity(),userService.findById(MOCK_ID));
    }

    private UserEntity returnObjectUserEntity() {
        return new UserEntity(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,ENCRYPTED_PASSWORD,MOCK_VALUE);
    }

    private UserEntity returnWithNullValueTheObjectUserEntity() {
        return new UserEntity(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,MOCK_PASSWORD,null);
    }

    private UserEntity returnWithDepositAmountAddedTheObjectUserEntity() {
        return new UserEntity(MOCK_ID,MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,
                MOCK_EMAIL,ENCRYPTED_PASSWORD,MOCK_VALUE.add(MOCK_DEPOSIT_AMOUNT));
    }

    private DepositRequestDTO returnObjectDepositRequestDTO() {
        return new DepositRequestDTO(MOCK_ID,MOCK_PASSWORD,MOCK_DEPOSIT_AMOUNT);
    }
}
