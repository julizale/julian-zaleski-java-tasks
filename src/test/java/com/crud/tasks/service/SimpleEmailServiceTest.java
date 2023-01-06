package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Test
    void shouldSendEmail() {
        //Given
        Mail mail = Mail.builder()
                .receiverEmail("test@test.com")
                .subject("Test")
                .message("Test Message")
                .toCc("test2@test.com")
                .build();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getReceiverEmail());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());

        //When
        simpleEmailService.send(mail);
        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}