package com.orange.cashplus.config;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;



public class EmailUtil {

    private static final String SENDER_EMAIL = ""; 
    private static final String SENDER_PASSWORD = ""; 
    private static final String APP_BASE_URL = "http://localhost:8080/cashplus";

    public static boolean sendVerificationEmail(String recipient, String token) {
        String subject = "Verify Your Email";
        String verificationLink = APP_BASE_URL + "/verify?token=" + token;
        String body = "Hello,\n\nClick the link below to verify your email:\n" + verificationLink +
                     "\n\nIf you didn't request this, please ignore this email.\n\nBest regards,\nCashPlus Team";
        
        return sendEmail(recipient, subject, body);
    }

    public static boolean sendPasswordResetEmail(String recipient, String token) {
        String subject = "Password Reset Request";
        String resetLink = APP_BASE_URL + "/reset-password?token=" + token;
        String body = "Hello,\n\nClick the link below to reset your password:\n" + resetLink +
                     "\n\nThis link will expire in 24 hours.\n\nIf you didn't request this, please ignore this email.\n\nBest regards,\nCashPlus Team";
        
        return sendEmail(recipient, subject, body);
    }

    private static boolean sendEmail(String recipient, String subject, String body) {
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

            Transport.send(message);
            System.out.println("Email sent successfully to: " + recipient);
            return true;
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}