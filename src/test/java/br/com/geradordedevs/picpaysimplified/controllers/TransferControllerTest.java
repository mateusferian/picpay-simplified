package br.com.geradordedevs.picpaysimplified.controllers;

import br.com.geradordedevs.picpaysimplified.dtos.requests.TransferRequestDTO;
import br.com.geradordedevs.picpaysimplified.facades.TransferFacade;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransferController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferFacade transferFacade;

    private final String TRANSFER_ROUTE = "/api/v1/transfers";

    private final Long MOCK_PAYER = 1L;

    private final String MOCK_PASSWORD = "12345";

    private final BigDecimal MOCK_TRANSACTION_AMOUNT = new BigDecimal(10);

    private final BigDecimal MOCK_TRANSACTION_AMOUNT_BELOW_THE_MINIMUM = new BigDecimal(0);

    private final BigDecimal MOCK_TRANSACTION_AMOUNT_ABOVE_THE_MAXIMUM = new BigDecimal(100001);

    private final Long MOCK_PAYEE = 2L;

    private TransferRequestDTO returnCorrectTransferRequestDTO() {
        return new TransferRequestDTO(MOCK_PAYER,MOCK_PASSWORD,MOCK_TRANSACTION_AMOUNT,MOCK_PAYEE);
    }

    private TransferRequestDTO returningObjectWithPayerNullFromTransferRequestDTO() {
        return new TransferRequestDTO(null,MOCK_PASSWORD,MOCK_TRANSACTION_AMOUNT,MOCK_PAYEE);
    }

    private TransferRequestDTO returningObjectWithPasswordNullFromTransferRequestDTO() {
        return new TransferRequestDTO(MOCK_PAYER,null,MOCK_TRANSACTION_AMOUNT,MOCK_PAYEE);
    }

    private TransferRequestDTO returningObjectWithTransactionAmountNullFromTransferRequestDTO() {
        return new TransferRequestDTO(MOCK_PAYER,MOCK_PASSWORD,null,MOCK_PAYEE);
    }

    private TransferRequestDTO returningObjectWithPayeeNullFromTransferRequestDTO() {
        return new TransferRequestDTO(MOCK_PAYER,MOCK_PASSWORD,MOCK_TRANSACTION_AMOUNT,null);
    }

    private TransferRequestDTO returningObjectWithTransactionAmountBelowTheMinimumFromTransferRequestDTO() {
        return new TransferRequestDTO(MOCK_PAYER,MOCK_PASSWORD,MOCK_TRANSACTION_AMOUNT_BELOW_THE_MINIMUM,MOCK_PAYEE);
    }

    private TransferRequestDTO returningObjectWithTransactionAmountAboveTheMaximumFromTransferRequestDTO() {
        return new TransferRequestDTO(MOCK_PAYER,MOCK_PASSWORD,MOCK_TRANSACTION_AMOUNT_ABOVE_THE_MAXIMUM,MOCK_PAYEE);
    }

    @Test
    public void transferMustReturnOK() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSFER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnCorrectTransferRequestDTO()))
        ).andExpect(status().isOk());
    }

    @Test
    public void transferPassingNullPayerMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSFER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithPayerNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void transferPassingNullPasswordMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSFER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithPasswordNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void transferPassingNullTransactionAmountMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSFER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithTransactionAmountNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void transferPassingNullPayeeMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSFER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithPayeeNullFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void transferPassingTransactionAmountBelowTheMinimumMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSFER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithTransactionAmountBelowTheMinimumFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void transferPassingTransactionAmountAboveTheMaximumMustReturnBadRequest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(TRANSFER_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returningObjectWithTransactionAmountAboveTheMaximumFromTransferRequestDTO()))
        ).andExpect(status().isBadRequest());
    }
}

