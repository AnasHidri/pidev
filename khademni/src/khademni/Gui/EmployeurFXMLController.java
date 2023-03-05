/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Offre;
import khademniService.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class EmployeurFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */

  @FXML
    private Button btnAdd;

    @FXML
    private Button btnIntPr;
    @FXML
    private Button btnDelete;
  
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnListe;

    @FXML
    private TableColumn<?, ?> colAdresse_societe;

    @FXML
    private TableColumn<?, ?> colDate_debut;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine_offre;

        @FXML
    private TableColumn<?, ?> colEtat;

    @FXML
    private TableColumn<?, ?> colTitre;
    

    @FXML
    private TextField tfAdressse_societe;

  

    @FXML
    private DatePicker tfDate_debut;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfDomaine_offre;

    @FXML
    private TextField tfTitre;

    @FXML
    private TableView<Offre> tvOffre;
  
    @FXML
    private TextField tfRecherche;
    @FXML
    private TextField tfEtat;
    

     @Override
    public void initialize(URL location, ResourceBundle resources) {
     showOffre1();
        
    }
    
    
    @FXML
    void RechercheOffre(ActionEvent event) {


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

        sortList.comparatorProperty().bind(tvOffre.comparatorProperty());
        tvOffre.setItems(sortList);
    
    }

    
public void showOffre1(){
  OffreService os =new OffreService(); 
ObservableList<Offre> list1 = os.afficherOffre();
colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse_societe.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine_offre.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDate_debut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

    
       tvOffre.setItems(list1);
}  
 @FXML
    public void AddOffre(ActionEvent event) {
        Offre o;
        OffreService os=new OffreService();
       String titre= tfTitre.getText();
      String des= tfDescription.getText(); 
     String soc= tfAdressse_societe.getText(); 
     String off=  tfDomaine_offre.getText();
    LocalDate date_debut = tfDate_debut.getValue();
Date datedeb = (date_debut == null) ? null : Date.valueOf(date_debut);

LocalDate currentDate = LocalDate.now();
    if(titre.isEmpty() || des.isEmpty() || soc.isEmpty()|| off.isEmpty()) {
      System.out.println();
       Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Tous les champs doivent être remplis");
    alert.showAndWait();
    } 
else if(date_debut == null) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Veuillez sélectionner une date de début pour l'offre.");
    alert.showAndWait();
} 

else if (!date_debut.equals(currentDate)) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("La date de début doit être égale à la date actuelle.");
    alert.showAndWait();
}


else {
            o =new Offre(2, titre, des, soc, off, datedeb);

         os.ajouterOffre(o);
          Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Offre Ajoutée");
    alert.showAndWait();    
             
  tvOffre.refresh();
  showOffre1();
   String senderEmail = "khademni.serviceClient@gmail.com"; 
String senderPassword = "iptppxmbutpkhtee"; 
String receiverEmail = "achour.rihab2000@gmail.com"; 
String subject = "Une offre Ajoutée";
String message = "Une nouvelle offre a été Ajoutée avec succès.";

try {
    sendEmail(senderEmail, senderPassword, receiverEmail, subject, message);
} catch (MessagingException ex) {
    System.out.println("Erreur lors de l'envoi de l'email : " + ex.getMessage());
}
     

    
   
    }
    }
   
    @FXML
 public void DeleteOffre(ActionEvent event) {
        OffreService os=new OffreService();
           Offre of = tvOffre.getSelectionModel().getSelectedItem();
           System.out.println(of.getId_offre());
       os.supprimerOffre(of);
         Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Offre Supprimée!");
    alert.showAndWait();
   
      os.afficherOffre();
      
        tvOffre.refresh();
  showOffre1();
    String senderEmail = "khademni.serviceClient@gmail.com"; 
String senderPassword = "iptppxmbutpkhtee"; 
String receiverEmail = "achour.rihab2000@gmail.com"; 
String subject = "Une offre Supprimée";
String message = "Une nouvelle offre a été Supprimée avec succès.";

try {
    sendEmail1(senderEmail, senderPassword, receiverEmail, subject, message);
} catch (MessagingException ex) {
    System.out.println("Erreur lors de l'envoi de l'email : " + ex.getMessage());
}
  

     
    }
    @FXML
    void IntP(ActionEvent event) throws IOException {
  SceneController SC= new SceneController();
         SC.Scene4(event);
    }
       @FXML
    void update(ActionEvent event) throws IOException {
       Offre selectedOffre = tvOffre.getSelectionModel().getSelectedItem();

    if (selectedOffre == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une Offre à modifier !");
        alert.showAndWait();
        return;
    }

    
    String titre = tfTitre.getText();  
    String description = tfDescription.getText();
    String adresse_societe = tfAdressse_societe.getText();
    String domaine_offre = tfDomaine_offre.getText(); 
    LocalDate date_debut =tfDate_debut.getValue();
    Date datedeb= Date.valueOf(date_debut);
  
    selectedOffre.setTitre(titre);
    selectedOffre.setDescription(description);
    selectedOffre.setAdresse_societe(adresse_societe);
    selectedOffre.setDomaine_offre(domaine_offre);
    selectedOffre.setDate_debut(datedeb);
  
    
     if (selectedOffre.getEtat().equals("refuser")) {
    selectedOffre.setEtat("en attente");
}
      
  
    System.out.println(selectedOffre); 
    OffreService OS = new OffreService(); 
    OS.modifierOffre(selectedOffre);
    System.out.println(selectedOffre); 
   
    tvOffre.refresh();

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Offre Modifié!");
    alert.showAndWait();
      tvOffre.refresh();
 showOffre1();
    
     String senderEmail = "khademni.serviceClient@gmail.com"; 
String senderPassword = "iptppxmbutpkhtee"; 
String receiverEmail = "achour.rihab2000@gmail.com"; 
String subject = "Une offre Modifiée";
String message = "Une nouvelle offre a été Modifiée avec succès.";

try {
    sendEmail3(senderEmail, senderPassword, receiverEmail, subject, message);
} catch (MessagingException ex) {
    System.out.println("Erreur lors de l'envoi de l'email : " + ex.getMessage());
}
  
    }

     @FXML
void ListeCandidature(ActionEvent event)  {
    Offre selectedOff = tvOffre.getSelectionModel().getSelectedItem();
    if (selectedOff == null) {
        // Show an alert if no item is selected
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucune offre sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une offre avant de continuer.");
        alert.showAndWait();
        return;
    }
    
    int offreId = selectedOff.getId_offre();
    
    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/Gui/CandidatureEmployeurFXML.fxml"));
        Parent root = loader.load();
        CandidatureEmployeurFXMLController controleur = loader.getController(); 
        controleur.setTextFields(offreId);
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnListe.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch(IOException e){
        System.out.println(e.getCause().getMessage());
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


    Transport.send(email);

    System.out.println("Email sent successfully.");
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
    //email.setText("offre ajoutée avec succes");
    email.setText("offre Supprimée avec succes");
   // email.setText("offre modifiée avec succes");

    Transport.send(email);

    System.out.println("Email sent successfully.");
}

    
    
    public static void sendEmail3(String senderEmail, String senderPassword, String receiverEmail, String subject, String message) throws MessagingException {
   
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
    
   email.setText("offre modifiée avec succes");

    Transport.send(email);

    System.out.println("Email sent successfully.");
}

    
    
}