package br.com.geradordedevs.picpaysimplified.controllers;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaysimplified.enums.TypeOfUser;
import br.com.geradordedevs.picpaysimplified.facades.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    private final String USER_ROUTE = "/api/v1/users";

    private final String USER_ROUTE_ID = "/api/v1/users/1";

    private final String USER_ROUTE_DEPOSIT = "/api/v1/users/deposit";

    private final Long MOCK_ID = 1l;

    private final String MOCK_NAME = "name";

    private final TypeOfUser MOCK_TYPE_OF_USER = TypeOfUser.USER;

    private final String MOCK_EMAIL = "name@gmail.com";

    private final String MOCK_PASSWORD = "12345678";

    private final BigDecimal MOCK_DEPOSIT_AMOUNT = new BigDecimal(10);

    private final String MOCK_DOCUMENT_NUMBER = "12345678901";

    private final BigDecimal MOCK_DEPOSIT_AMOUNT_BELOW_THE_MINIMUM = new BigDecimal(0);

    private final BigDecimal MOCK_DEPOSIT_AMOUNT_ABOVE_THE_MAXIMUM = new BigDecimal(100001);

    private final String MOCK_NAME_BELOW_THE_MINIMUM ="an";

    private final String MOCK_NAME_ABOVE_THE_MAXIMUM ="12345678912345678nehnvowedwedwedwrhvioervioeriierev";

    private final String MOCK_DOCUMENT_NUMBER_BELOW_THE_MINIMUM ="1234567890";

    private final String MOCK_DOCUMENT_NUMBER_ABOVE_THE_MAXIMUM ="123456789012345";

    private final String MOCK_PASSWORD_BELOW_THE_MINIMUM ="1234567";

    private final String MOCK_PASSWORD_ABOVE_THE_MAXIMUM ="123456789012345678901";

    private final String MOCK_INVALID_EMAIL = "invalid";


    private UserRequestDTO returnCorrectUserRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserRequestDTO returningObjectWithNameNullFromTransferRequestDTO() {
        return new UserRequestDTO(null,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserRequestDTO returningObjectWithDocumentNumberNullFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,null,MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserRequestDTO returningObjectWithEmailNullFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,null,MOCK_PASSWORD);
    }

    private UserRequestDTO RreturningObjectWithPasswordNullFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_EMAIL,null);
    }

    private UserRequestDTO returningObjectWithCharactersAboveTheMaximumOfTheNameVariableFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME_ABOVE_THE_MAXIMUM,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserRequestDTO returningObjectWithCharactersBelowTheMinimumOfTheNameVariableFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME_BELOW_THE_MINIMUM,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserRequestDTO returningObjectWithCharactersAboveTheMaximumOfTheDocumentNumberVariableFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER_ABOVE_THE_MAXIMUM,MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserRequestDTO returningObjectWithCharactersBelowTheMinimumOfTheDocumentNumberVariableFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER_BELOW_THE_MINIMUM,MOCK_EMAIL,MOCK_PASSWORD);
    }

    private UserRequestDTO returningObjectWithCharactersAboveTheMaximumOfThePasswordVariableFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_EMAIL,MOCK_PASSWORD_ABOVE_THE_MAXIMUM);
    }

    private UserRequestDTO returningObjectWithCharactersBelowTheMinimumOfThePasswordVariableFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_EMAIL,MOCK_PASSWORD_BELOW_THE_MINIMUM);
    }

    private UserRequestDTO returningObjectPassingEmailVariableWithInvalidEmailFromTransferRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_INVALID_EMAIL,MOCK_PASSWORD);
    }

    private DepositRequestDTO returnCorrectDepositRequestDTO() {
        return new DepositRequestDTO(MOCK_ID,MOCK_PASSWORD,MOCK_DEPOSIT_AMOUNT);
    }

    private DepositRequestDTO returningObjectWithUserNullFromTransferRequestDTO() {
        return new DepositRequestDTO(null,MOCK_PASSWORD,MOCK_DEPOSIT_AMOUNT);
    }

    private DepositRequestDTO returningObjectWithPasswordNullFromTransferRequestDTO() {
        return new DepositRequestDTO(MOCK_ID,null,MOCK_DEPOSIT_AMOUNT);
    }

    private DepositRequestDTO returningObjectWithDepositAmountNullFromTransferRequestDTO() {
        return new DepositRequestDTO(MOCK_ID,MOCK_PASSWORD,null);
    }

    private DepositRequestDTO returningObjectWithDepositAmountBelowTheMinimumFromDepositRequestDTO() {
        return new DepositRequestDTO(MOCK_ID,MOCK_PASSWORD,MOCK_DEPOSIT_AMOUNT_BELOW_THE_MINIMUM);
    }

    private DepositRequestDTO returningObjectWithDepositAmountAboveTheMaximumFromDepositRequestDTO() {
        return new DepositRequestDTO(MOCK_ID,MOCK_PASSWORD,MOCK_DEPOSIT_AMOUNT_ABOVE_THE_MAXIMUM);
    }

    @Test
    public void findByIdMustReturnOK() throws Exception {
        mockMvc.perform(get(USER_ROUTE_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void saveMustReturnCreated() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnCorrectUserRequestDTO()))
        ).andExpect(status().isCreated());
    }

    @Test
    public void saveMPassingNullNameMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithNameNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveMPassingNullDocumentNumberMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithDocumentNumberNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveMPassingNullEmailMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithEmailNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void saveMPassingNullPasswordMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(RreturningObjectWithPasswordNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void savePassingCharactersAboveTheMaximumOfTheNameVariableMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithCharactersAboveTheMaximumOfTheNameVariableFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void savePassingCharactersBelowTheMinimumOfTheNameVariableMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithCharactersBelowTheMinimumOfTheNameVariableFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void savePassingCharactersAboveTheMaximumOfTheDocumentNumberVariableMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithCharactersAboveTheMaximumOfTheDocumentNumberVariableFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void savePassingCharactersBelowTheMinimumOfTheDocumentNumberVariableMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithCharactersBelowTheMinimumOfTheDocumentNumberVariableFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void savePassingCharactersAboveTheMaximumOfThePasswordVariableMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithCharactersAboveTheMaximumOfThePasswordVariableFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void savePassingCharactersBelowTheMinimumOfThePasswordVariableMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithCharactersBelowTheMinimumOfThePasswordVariableFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void savePassingEmailVariableWithInvalidEmailMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectPassingEmailVariableWithInvalidEmailFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void depositMustReturnOK() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE_DEPOSIT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnCorrectDepositRequestDTO()))
        ).andExpect(status().isOk());
    }

    @Test
    public void depositPassingNullUserMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE_DEPOSIT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithUserNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void depositPassingNullPasswordMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE_DEPOSIT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithPasswordNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void depositPassingNullDepositAmountMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE_DEPOSIT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithDepositAmountNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void depositPassingDepositAmountBelowTheMinimumMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE_DEPOSIT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithDepositAmountBelowTheMinimumFromDepositRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void depositPassingDepositAmountAboveTheMaximumMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(USER_ROUTE_DEPOSIT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithDepositAmountAboveTheMaximumFromDepositRequestDTO()))
        ).andExpect(status().isBadRequest());
    }
}
