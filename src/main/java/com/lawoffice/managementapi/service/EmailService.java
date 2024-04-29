package com.lawoffice.managementapi.service;

import com.lawoffice.managementapi.dto.EmailValueDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;
    @Value("${mail.urlFront}")
    private String urlFront;

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("marcosplata98@gmail.com");
        message.setTo("marcosplata98@gmail.com");
        message.setSubject("Envio email simple");
        message.setText("Contenido del email");
        javaMailSender.send(message);
    }

    public void sendEmailTemplate(EmailValueDto dto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("userName", dto.getUserName());
            model.put("url", urlFront + dto.getJwt());
            context.setVariables(model);
            String htmlText = templateEngine.process("email-template", context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmlText, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {

        }
    }


}
