/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import api.MailService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import javafx.stage.FileChooser;

import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormateurFXMLController implements Initializable {
    
    

    
         @FXML
    private Button pdf;
         @FXML 
    private ToggleButton tbn;
         
         @FXML
    private Button btnmail;
         
     /* 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
    
   @FXML
    private TextField filePath;

    public void selectFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter(
        "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath.setText(selectedFile.getPath());
            System.out.println("filepath::"+filePath.getText());
        }
    }
    
    public void openFile(){
 String filePath2 = filePath.getText(); // get file path from the database

// Create a new file object with the file path
File file = new File(filePath2);

// Check if the file exists and is a file
if (file.exists() && file.isFile()) {
    // Get the desktop object
    Desktop desktop = Desktop.getDesktop();

    try {
        // Open the file using the desktop object
        desktop.open(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
} else {
    // The file does not exist or is not a file
    System.out.println("File does not exist or is not a file.");
}
    }
    
    @FXML
public void SendMail() throws GeneralSecurityException{
    
    try {
                 MailService.sendEmail("ymahfoudh55@gmail.com", "test","bonjour");
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    
    
           /*  try {
                 MailService.sendEmail("yassine.mahfoudh@esprit.tn", "test","bonjour");
             } catch (MessagingException ex) {
                 System.out.println("ex::"+ex.getMessage());
             }*/
         /*  ExecutorService executor = Executors.newSingleThreadExecutor();
executor.execute(new Runnable() {
    public void run() {
        try {
                 MailService.sendEmail("anashidri36@gmail.com", "test","bonjour");
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
});
executor.shutdown();*/
           
}
    
    /*
    @FXML
public void SendMail(){
    String username = "ymahfoudh55@example.com";
    String password = "Yassine240118";
    String recipient = "yassine.mahfoudh@esprit.tn";
    String subject = "Test";
    String body = "Bonjour";
    
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);

        System.out.println("Message sent successfully!");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
    */
}
