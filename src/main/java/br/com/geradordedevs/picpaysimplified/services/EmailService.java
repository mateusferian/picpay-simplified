package br.com.geradordedevs.picpaysimplified.services;

import java.math.BigDecimal;

public interface EmailService {
     void sendEmail(String email, BigDecimal transactionAmount,String namePayer);
}
