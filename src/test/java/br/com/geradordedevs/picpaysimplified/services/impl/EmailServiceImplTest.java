package br.com.geradordedevs.picpaysimplified.services.impl;

import br.com.geradordedevs.picpaysimplified.exceptions.EmailException;
import com.google.common.base.Verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;
    
    @Mock
    private JavaMailSender javaMailSender;

    private final String MOCK_EMAIL = "name@gmail.com";

    private final BigDecimal MOCK_TRANSACTION_AMOUNT = new BigDecimal(10);

    private final String MOCK_NAME = "name";

    @Test
    public void sendEmailMustReturnOk() throws Exception {
        emailService.sendEmail(MOCK_EMAIL,MOCK_TRANSACTION_AMOUNT,MOCK_NAME);
        verify(javaMailSender,timeout(1)).send(returnMessage());
    }

    private SimpleMailMessage returnMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(MOCK_EMAIL);
        message.setText("transfer received in the amount of US$" + MOCK_TRANSACTION_AMOUNT + " made by payer: " + MOCK_NAME);
        message.setSubject("PicPay informs, You have received a transfer");

        return message;
    }
}
