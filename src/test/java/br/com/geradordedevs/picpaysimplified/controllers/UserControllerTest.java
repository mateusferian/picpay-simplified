package br.com.geradordedevs.picpaysimplified.controllers;

import br.com.geradordedevs.picpaysimplified.dtos.requests.DepositRequestDTO;
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

    private UserRequestDTO returnCorrectUserRequestDTO() {
        return new UserRequestDTO(MOCK_NAME,MOCK_TYPE_OF_USER,MOCK_DOCUMENT_NUMBER,MOCK_EMAIL,MOCK_PASSWORD);
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
}
