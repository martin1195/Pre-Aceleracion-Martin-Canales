package com.alkemy.disneyapi.service.impl;

import com.alkemy.disneyapi.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class EmailServiceImpl implements EmailService {
    @Value("${alkemy.disneyapi.email.sender}")
    private String emailSender;


    public void sendWelcomeEmailTo(String to) {
        String apiKey = System.getenv("EMAIL_API_KEY");
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Bienvenido/a a DisneyAPI"
        );
        String subject = "DisneyAPI";
        Mail mail = new Mail(fromEmail,subject,toEmail,content);
        SendGrid sendGrid = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException e) {
            throw new RuntimeException("Error trying to send the email");
        }
    }
}
