package com.milaev.medicine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private static Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;


    public boolean sendEmail (final String email) {
        boolean res = false;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {

                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
                    message.setFrom("t-med-prod@mail.ru");
                    message.setTo(email);
                    message.setSubject("Hello from BolniTchka!");
                    message.setSentDate(new Date());

                    String text = "new  events for you";

                    message.setText("There are new events for you. Please, connect with your doctor.",true);
                }
            };

            mailSender.send(preparator);
            res = true;

        } catch (Exception ex) {
            log.error("Eroor during sending email message", ex);
        }

        return res;
    }

}
