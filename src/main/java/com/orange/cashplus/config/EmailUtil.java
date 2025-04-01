package com.orange.cashplus.config;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    private static final String SENDER_EMAIL = "zakariajr963@gmail.com"; 
    private static final String SENDER_PASSWORD = "uner eqgv hkjp zlmr"; 

    public static void sendVerificationEmail(String recipient, String token) {
        String subject = "Verify Your Email";
        String verificationLink = "http://localhost:8080/cashplus/verify?token=" + token;
        String body = "Hello,\n\nClick the link below to verify your email:\n" + verificationLink +
                      "\n\nIf you didn't request this, please ignore this email.\n\nBest regards,\nCashPlus Team";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        session.setDebug(true); 

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

           
            try {
                Transport.send(message);
                System.out.println(" Verification email sent to: " + recipient);
            } catch (MessagingException e) {
                e.printStackTrace();
                System.err.println(" Failed to send verification email: " + e.getMessage());
            }

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println(" Error while creating email message: " + e.getMessage());
        }
    }

}
