package khademni.guiOffre;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import khademni.entity.Offre;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
import khademni.services.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class OffreAdminFXMLController implements Initializable {

    @FXML
    private Button btnRefuser;
    @FXML
    private Button btnRetourne;
  @FXML
    private Button btnvalide;
  @FXML
    private TextField tfSearch;

 @FXML
    private TableColumn<?, ?> colEtat;

    @FXML
    private TableColumn<?, ?> ColOffre;

    @FXML
    private TableColumn<?, ?> ColUser;

    @FXML
    private TableColumn<?, ?> colAdresse_societe;

    @FXML
    private TableColumn<?, ?> colCandid;

    
    @FXML
    private TableColumn<?, ?> colDate_debut;

    @FXML
    private TableColumn<?, ?> colDate_limite;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine_offre;

    @FXML
    private TableColumn<?, ?> colId_offre;

    @FXML
    private TableColumn<?, ?> colId_user;

    @FXML
    private TableColumn<?, ?> colTitre;

    @FXML
    private TableView<Offre> tvOffre;
         @FXML
      private ComboBox<String> liste_for;
    
    @FXML
       private ComboBox<String> liste_ev;
    @FXML
            private ComboBox<String> liste_off;
    @FXML
            private ComboBox<String> pani;
     @FXML
            private Button stat;
    @FXML
            private Button prof;


   
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showOffre();
           liste_for.getItems().addAll("Liste formation");
            liste_ev.getItems().addAll("Liste evenement");
            liste_off.getItems().addAll("Liste offre");
            pani.getItems().addAll("Liste user", "Liste activation");
            
               
           liste_for.setOnAction(event -> {
    String selectedPage = (String) liste_for.getSelectionModel().getSelectedItem();
if (selectedPage.equals("Liste formation")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiFormation/AdminF.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});

liste_ev.setOnAction(event -> {
    String selectedPage = (String) liste_ev.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste evenement")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/ListeEvenementAD.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } 
});

liste_off.setOnAction(event -> {
    String selectedPage = (String) liste_off.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste offre")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/OffreAdminFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } 
});

pani.setOnAction(event -> {
    String selectedPage = (String) pani.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste user")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiUser/UsersListFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Liste activation")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiUser/ActivationFormateurFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } 
});

    }   
    @FXML
    void Search(ActionEvent event) {

  OffreService os =new OffreService(); 
        FilteredList<Offre> filter = new FilteredList<>(os.afficherOffre(), e -> true);

        tfSearch.textProperty().addListener((Observable, oldValue, newValue) -> {

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

    public void showOffre(){
     OffreService os =new OffreService();
     ObservableList<Offre> list = os.afficherattenteOffre();
colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse_societe.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine_offre.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDate_debut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
    colEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

       tvOffre.setItems(list);
}
    


    @FXML
    void retourneInter(ActionEvent event) throws IOException {
         SceneController SC= new SceneController();
         SC.Scene4(event);
    }
    
    @FXML
    void RefuserOffre(ActionEvent event) {
     Offre selectedOffre = tvOffre.getSelectionModel().getSelectedItem();
     Offre o =new Offre();
  OffreService os= new OffreService();
    if (selectedOffre == null) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une Offre à Refusée !");
        alert.showAndWait();
        return;
          
    }os.RefuseOffre(selectedOffre);
    os.afficherattenteOffre();
    
   
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Offre Refusée!");
    alert.showAndWait();
     tvOffre.refresh();
  showOffre();
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
        
 
    
    
       @FXML
    void validerOffre(ActionEvent event) { 
         
     Offre selectedOffre = tvOffre.getSelectionModel().getSelectedItem();
     Offre o =new Offre();
  OffreService os= new OffreService();
    if (selectedOffre == null) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une Offre à Validée !");
        alert.showAndWait();
        return;
          
    }os.accepterOffre(selectedOffre);
    os.afficherattenteOffre();
    
   
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Offre Validée!");
    alert.showAndWait();
     tvOffre.refresh();
  showOffre();
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
    email.setText("offre Refusée avec succes");

    Transport.send(email);

    System.out.println("Email sent successfully.");
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
    email.setText("offre Acceptée avec succes");

    Transport.send(email);

    System.out.println("Email sent successfully.");
}

            @FXML
    private void Profile(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/gui/HistoriqueFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
               @FXML
    private void stat(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiUser/DashboardFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
    
       
       
}
 


