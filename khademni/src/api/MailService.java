/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import java.security.GeneralSecurityException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 *
 * @author ASUS
 */

public class MailService {
    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException, GeneralSecurityException {

    //String username = "securesally@gmail.com";
    String username = "ymahfoudh55@gmail.com";
    String password = "yyykjakrsaboljwp";

    // Set mail properties
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

    // Create session
    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
    session.setDebug(true);

    // Create email message
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(username));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
    message.setSubject(subject);
    message.setText(body);

    // Send email
    try {
        
        Transport tr = session.getTransport("smtp");
tr.connect("smtp.gmail.com", username, password);
message.saveChanges();      // don't forget this
tr.sendMessage(message, message.getAllRecipients());
tr.close();

   // Transport.send(message);
    System.out.println("mail envoyé");

} catch (MessagingException e) {
    // Handle the exception here
    e.getMessage();
    }
}

}