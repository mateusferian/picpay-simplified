package br.com.geradordedevs.picpaysimplified.services.impl;

import br.com.geradordedevs.picpaysimplified.exceptions.EmailException;
import br.com.geradordedevs.picpaysimplified.exceptions.enums.EmailEnum;
import br.com.geradordedevs.picpaysimplified.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String email, BigDecimal transactionAmount,String namePayer) {
        log.info("sending notification to email {}",email);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setText("transfer received in the amount of US$"+transactionAmount+" made by payer: "+ namePayer);
            message.setSubject("PicPay informs, You have received a transfer");

            javaMailSender.send(message);

            log.warn("email successfully sent");
        }catch (MailException mailException){
            throw new EmailException(EmailEnum.EMAIL_ERROR);
        }
    }
}
