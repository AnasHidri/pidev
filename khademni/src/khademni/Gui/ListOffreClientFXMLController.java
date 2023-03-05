/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static khademni.Gui.EmployeurFXMLController.sendEmail;
import khademni.entity.Candidature;
import khademni.entity.Offre;
import khademniService.CandidatureService;
import khademniService.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class ListOffreClientFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnPost;
    @FXML
    private Button btnretourne;
      @FXML
    private Button btnmescandid;
    
    @FXML
    private Button btnDetails;
  

    @FXML
    
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colTitre;
    @FXML
    private TableColumn<?, ?> colAdresse;
    @FXML
    private TableColumn<?, ?> colDateDebut;
    @FXML
    private TableColumn<?, ?> colDomaine;
        @FXML
    private TextField tfRecherche;

    @FXML
    void AddOffre(ActionEvent event) {

    }
    
    @FXML
    private TableView<Offre> tvOffre23;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     showOffre();
     loadOffre("cuisine");
    }    
      @FXML
    void Recherche(ActionEvent event) {
 

  OffreService os =new OffreService(); 
        FilteredList<Offre> filter = new FilteredList<>(os.afficherOffre(), e -> true);

        tfRecherche.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateOffreData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateOffreData.getTitre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateOffreData.getDomaine_offre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateOffreData.getAdresse_societe().toLowerCase().contains(searchKey)) {
                    return true;
              
                } else 
                    return false;
                
            });
        });

        SortedList<Offre> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(tvOffre23.comparatorProperty());
        tvOffre23.setItems(sortList);
    
    }

    private void loadOffre(String domaine_offre) {
        OffreService OS=new OffreService(); 
    ObservableList<Offre> listef = OS.afficherOffre();
    ObservableList<Offre> filteredList = FXCollections.observableArrayList();
    ObservableList<Offre> otherList = FXCollections.observableArrayList();
    for (int i = 0; i < listef.size(); i++) {
        Offre Offre = (Offre) listef.get(i);
        if (Offre.getDomaine_offre().equals(domaine_offre)) {
            filteredList.add(Offre);
        } else {
            otherList.add(Offre);
        }
    }
    filteredList.addAll(otherList);
    colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse_societe"));
    colDomaine.setCellValueFactory(new PropertyValueFactory<>("domaine_offre"));
    colDateDebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
    tvOffre23.setItems(filteredList);
}

    
     public void showOffre(){
           OffreService os =new OffreService(); 

ObservableList<Offre> list23 = os.afficherAccepterOffre();

colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDateDebut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
    
       tvOffre23.setItems(list23);   
}
     
   @FXML
    void AddCandidature(ActionEvent event) throws IOException {
         Offre selectedOffre = tvOffre23.getSelectionModel().getSelectedItem();
        System.out.println("id_o::"+selectedOffre.getId_offre());
        Candidature C= new Candidature(selectedOffre.getId_offre(), 2,"en attente");
        
        CandidatureService CV= new CandidatureService();
        CV.ajouterCandidature(C);
       String senderEmail = "khademni.serviceClient@gmail.com"; 
       String senderPassword = "iptppxmbutpkhtee"; 
       String receiverEmail = "achour.rihab2000@gmail.com"; 
       String subject = "Une offre Postuler";
       String message = "Une nouvelle offre a été Postuler avec succès.";

try {
    sendEmail(senderEmail, senderPassword, receiverEmail, subject, message);
} catch (MessagingException ex) {
    System.out.println("Erreur lors de l'envoi de l'email : " + ex.getMessage());
}
        
    }
    
    
  
    
      @FXML
    void retourneinterPrin(ActionEvent event) throws IOException {
    SceneController SC = new SceneController();
    SC.Scene4(event);
      

    }
    
     @FXML
    void MesCandidature(ActionEvent event) throws IOException {
                
 SceneController SC = new SceneController();
    SC.Scene6(event);
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

    email.setSubject("Postulation");
    email.setText("offre Postuler avec succes");

    Transport.send(email);

    System.out.println("Email sent successfully.");
}

        @FXML
    void AnnulerCandidature(ActionEvent event) {
 Offre selectedOffre = tvOffre23.getSelectionModel().getSelectedItem();
        System.out.println("id_o::"+selectedOffre.getId_offre());
        Candidature C= new Candidature(selectedOffre.getId_offre(), 2,"en attente");
        CandidatureService CV= new CandidatureService();
        CV.supprimerCandidature(C);
         String senderEmail = "khademni.serviceClient@gmail.com"; 
String senderPassword = "iptppxmbutpkhtee"; 
String receiverEmail = "achour.rihab2000@gmail.com"; 
String subject = "Postulation Annuler";
String message = "Une  offre a été Annuler.";

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

    email.setSubject("Postulation");
    email.setText("Postulation Annuler ");

    Transport.send(email);

    System.out.println("Email sent successfully.");
}

}

