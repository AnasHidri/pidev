/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static khademni.Gui.OffreAdminFXMLController.sendEmail;
import static khademni.Gui.OffreAdminFXMLController.sendEmail1;
import khademni.entity.Candidature;
import khademni.entity.Offre;
import khademni.entity.User;
import khademniService.CandidatureService;
import khademniService.OffreService;


/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class CandidatureEmployeurFXMLController implements Initializable {

 @FXML
    private TableColumn<?, ?> ColEtat;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colTitre;

    @FXML
    private TableView<Offre> tvCandidature1;

    @FXML
    private TableView<User> tvCandidature2;

    @FXML
    private TableView<Candidature> tvCandidature3;
    @FXML
    private Button btnretourne;
    @FXML
    private Button btnRefuser;

    @FXML
    private Button btnvalide;
    @FXML
    private TextField id_offre1;
       @FXML
    private TextField Titre;
       @FXML
    private TextField Email;

    @FXML
    private TextField Etat;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


}     
    
 public void showCandidature1(int id_offre){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Offre> listeCandidature1 = cs.afficherOffreEmployeur1(id_offre);

colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));

       tvCandidature1.setItems(listeCandidature1);
} 

  public void showCandidature2(int id_offre) {
    CandidatureService cs = new CandidatureService();
    ObservableList<User> listeCandidature2 = cs.afficherUserEmployeur2(id_offre);
    
    colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    
    tvCandidature2.setItems(listeCandidature2);
}

public void showCandidature3(int id_offre) {
    CandidatureService cs = new CandidatureService();
    ObservableList<Candidature> listeCandidature3 = cs.afficherCandidatureEmployeur3(id_offre);

    ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));

    tvCandidature3.setItems(listeCandidature3);
}


  private int OffreId;
      
     public void SetOffreId(int OffreId) {
    this.OffreId = OffreId;
}
    public void setTextFields(int OffreId){
        id_offre1.setText(OffreId+"");
         showCandidature1(OffreId);

       showCandidature2(OffreId);
       
       showCandidature3(OffreId);
    }
 
 
   @FXML
    void refusCandidat(ActionEvent event) {
    Candidature selectedCandidature = tvCandidature3.getSelectionModel().getSelectedItem();
       int offreId = selectedCandidature.getId_offre();
System.out.println("id_u::"+selectedCandidature.getId_offre());
     int userId = selectedCandidature.getId_user();
System.out.println("id_o::"+selectedCandidature.getId_user());
     Candidature C =new Candidature();
  CandidatureService cs= new CandidatureService();
    if (selectedCandidature == null) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une Candidature à Refusé !");
        alert.showAndWait();
        return;}
          
cs.RefuserCandidature(selectedCandidature,selectedCandidature.getId_offre());   
cs.afficherCandidatureClient2();
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Candidature Refusé!");
    alert.showAndWait();
     tvCandidature3.refresh();
    String senderEmail = "khademni.serviceClient@gmail.com"; 
String senderPassword = "iptppxmbutpkhtee"; 
String receiverEmail = "achour.rihab2000@gmail.com"; 
String subject = "Une offre Refusée";
String message = "Une nouvelle offre a été Refusée avec succès.";

try {
    sendEmail1(senderEmail, senderPassword, receiverEmail, subject, message);
} catch (MessagingException ex) {
    System.out.println("Erreur lors de l'envoi de l'email : " + ex.getMessage());
}
    }
     
    
     public static void sendEmail1(String senderEmail, String senderPassword, String receiverEmail, String subject, String message) throws MessagingException {
   
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("khademni.serviceClient@gmail.com", "iptppxmbutpkhtee");
        }
    });

    Message email = new MimeMessage(session);
    email.setFrom(new InternetAddress("khademni.serviceClient@gmail.com"));
    email.setRecipients(Message.RecipientType.TO, InternetAddress.parse("achour.rihab2000@gmail.com"));

    email.setSubject("offre");
    email.setText(" Malheureusement Votre offre est Refusée ");

    Transport.send(email);

    System.out.println("Email sent successfully.");
}

    @FXML
    void validerCandidat(ActionEvent event) {
 Candidature selectedCandidature = tvCandidature3.getSelectionModel().getSelectedItem();
       int offreId = selectedCandidature.getId_offre();
       System.out.println("id_o::"+selectedCandidature.getId_offre());

        int userId = selectedCandidature.getId_user();
System.out.println("id_u::"+selectedCandidature.getId_user());
     Candidature C =new Candidature();
  CandidatureService cs= new CandidatureService();
    if (selectedCandidature == null) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une Candidature à Accepté !");
        alert.showAndWait();
        return;
          
    }cs.AccepterCandidature(selectedCandidature,selectedCandidature.getId_offre());
    cs.afficherCandidatureClient2();
     
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Candidature Acceptée!");
    alert.showAndWait();
     tvCandidature3.refresh();

   String senderEmail = "khademni.serviceClient@gmail.com"; 
String senderPassword = "iptppxmbutpkhtee"; 
String receiverEmail = "achour.rihab2000@gmail.com"; 
String subject = "Une offre Acceptée";
String message = "Une nouvelle offre a été Acceptée avec succès.";

try {
    sendEmail(senderEmail, senderPassword, receiverEmail, subject, message);
} catch (MessagingException ex) {
    System.out.println("Erreur lors de l'envoi de l'email : " + ex.getMessage());
}
    }
    
public static void sendEmail(String senderEmail, String senderPassword, String receiverEmail, String subject, String message) throws MessagingException {
   
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("khademni.serviceClient@gmail.com", "iptppxmbutpkhtee");
        }
    });

    Message email = new MimeMessage(session);
    email.setFrom(new InternetAddress("khademni.serviceClient@gmail.com"));
    email.setRecipients(Message.RecipientType.TO, InternetAddress.parse("achour.rihab2000@gmail.com"));

    email.setSubject("offre");
    email.setText(" Félicitation Votre offre est Acceptée ");

    Transport.send(email);

    System.out.println("Email sent successfully.");
}


   @FXML
    void retourne(ActionEvent event) throws IOException{
        SceneController SC =new SceneController();
        SC.Scene5(event);
    }
}