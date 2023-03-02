/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Evenement;
import khademni.entity.Participation;
import khademni.services.EvenementService;
import khademni.services.ParticipationService;
import java.sql.Date;
import static java.util.Collections.list;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.*;



/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeEvenementController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
     private TableView<Evenement> tab_ev_liste;
      @FXML
     private TableColumn titre_list;
      
      @FXML
     private TableColumn desc_list;
      @FXML
     private TableColumn nom_soc_list;
      @FXML
     private TableColumn lieu_list;
      @FXML
     private TableColumn date_deb_list;
      @FXML
     private TableColumn date_fin_list;
      @FXML
     private Button select_ev_list;
      @FXML
     private Button prti_ev_list;
       @FXML
    private TextField idevl;
       @FXML
        private Button btn_consulte;
      @FXML
      private TextField recherche_text_par;
      @FXML
      private Button btn_recherche;
       @FXML
     private DatePicker date_deb;
       @FXML
     private DatePicker datefin;
       @FXML
       private Button filtre;
       
        
        
        
       
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListEV();
    }    
    
    @FXML
public void ListEV(){
    EvenementService es= new EvenementService();
    ObservableList<Evenement> list = es.getAll();
    
    // Filtrer la liste pour n'afficher que les événements dont la date de fin est supérieure à la date actuelle
    LocalDate currentDate = LocalDate.now();
    list = list.filtered(e -> new java.sql.Date(e.getDate_fin().getTime()).toLocalDate().isAfter(currentDate));
    
    System.out.println("list ::: "+list);
    titre_list.setCellValueFactory(new PropertyValueFactory<>("titre"));
    desc_list.setCellValueFactory(new PropertyValueFactory<>("description"));
    nom_soc_list.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
    lieu_list.setCellValueFactory(new PropertyValueFactory<>("lieu"));
    date_deb_list.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
    date_fin_list.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
  
    tab_ev_liste.setItems(list);
}
    
    @FXML
    private void SelectEV(ActionEvent event){
        Evenement selectedEV =  tab_ev_liste.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idevl.setText(String.valueOf(selectedEV.getId_evenement()));

      //  titre_list.setText(selectedEV.getTitre());
       // desc_list.setText(selectedEV.getDescription());
       // nom_soc_list.setText(selectedEV.getNom_societe());
        //lieu_list.setText(selectedEV.getLieu());
    }
    
   @FXML
private void ParticipeEV(ActionEvent event){
    Evenement selectedEV =  tab_ev_liste.getSelectionModel().getSelectedItem();
    System.out.println("id_e::"+selectedEV.getId_evenement());
    idevl.setText(String.valueOf(selectedEV.getId_evenement()));
    Participation p= new Participation(selectedEV.getId_evenement(), 2,"active");
    System.out.println("tese1");
    ParticipationService ps= new ParticipationService();
    ps.ajouterParticipation(p);
    System.out.println("tese2");

    // Envoi du mail
    String to = "oueslati.yassmine1@gmail.com"; // Adresse mail de l'utilisateur
    String from = "khademni.serviceClient@gmail.com"; // Votre adresse mail
    String host = "smtp.gmail.com"; // Adresse du serveur SMTP (ici, Gmail)
    String username = "oueslati.yassmine1@gmail.com"; // Votre adresse mail
    String password = "iptppxmbutpkhtee"; // Votre mot de passe Gmail
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "587");
    Session session = Session.getInstance(properties, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Notification de participation à un événement");
        message.setText("Bonjour,\n\nVous avez participé à l'événement "+selectedEV.getTitre()+"\n\nCordialement,\nKhademni");
        Transport.send(message);
        System.out.println("Le message a été envoyé avec succès.");
    } catch (MessagingException mex) {
        mex.printStackTrace();
    }
}
    
    @FXML
    private void AnnulerP(ActionEvent event){
        Evenement selectedEV =  tab_ev_liste.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idevl.setText(String.valueOf(selectedEV.getId_evenement()));
        System.out.println("tese1");
         ParticipationService ps= new ParticipationService();
         ps.supprimerParticipation(selectedEV);
        System.out.println("tese2");
        
         // Envoi du mail
    String to = "oueslati.yassmine1@gmail.com"; // Adresse mail de l'utilisateur
    String from = "khademni.serviceClient@gmail.com"; // Votre adresse mail
    String host = "smtp.gmail.com"; // Adresse du serveur SMTP (ici, Gmail)
    String username = "oueslati.yassmine1@gmail.com"; // Votre adresse mail
    String password = "iptppxmbutpkhtee"; // Votre mot de passe Gmail
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "587");
    Session session = Session.getInstance(properties, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Notification de l'annulation de la participation à un événement");
        message.setText("Bonjour,\n\nVous avez annulé votre participation à l'événement "+selectedEV.getTitre()+"\n\nCordialement,\nKhademni");
        Transport.send(message);
        System.out.println("Le message a été envoyé avec succès.");
    } catch (MessagingException mex) {
        mex.printStackTrace();
    }
    }
    
     @FXML
    private void ConsulterP(ActionEvent event) throws IOException{
try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesParticipations.fxml"));
             Parent root = loader.load();

              Scene scene = new Scene(root);
               Stage stage = (Stage) btn_consulte.getScene().getWindow();
                stage.setScene(scene);
                 stage.show();

                  }catch(IOException ex){
                      System.out.println(ex.getCause().getMessage());
                      }
    }
    
   /* @FXML
    private void recherche_par(ActionEvent event) {
            // Récupérer l'ID de la fomation à rechercher depuis le champ de texte
    String titre = recherche_text_par.getText();

    // Appeler le service pour récupérer la formation correspondant à l'ID
    Evenement EvenementRecherche = (Evenement) sp.readById(titre);

    if (EvenementRecherche != null) {
        // Afficher l formation trouvé dans la ListView
        ObservableList<Participation> Participations = FXCollections.observableArrayList(participationRecherche);
        list2.setItems(Participations);
    } else {
        // Afficher un message d'erreur si la formation n'a pas été trouvé
        System.out.println("Participation non trouvée");
    }
    }*/
    
    
   @FXML
private void rechercheEvenement(ActionEvent event) {
    // Récupérer le titre de l'événement à rechercher depuis le champ de texte
    String titreEvenement = recherche_text_par.getText();

    // Récupérer la liste des événements affichée dans la TableView
    ObservableList<Evenement> evenements = tab_ev_liste.getItems();

    // Créer une nouvelle liste pour stocker les résultats de la recherche
    ObservableList<Evenement> evenementsRecherche = FXCollections.observableArrayList();

    // Parcourir la liste des événements et ajouter les événements correspondant au titre de recherche
    for (Evenement evenement : evenements) {
        if (evenement.getTitre().toLowerCase().contains(titreEvenement.toLowerCase())) {
            evenementsRecherche.add(evenement);
        }
    }

    if (!evenementsRecherche.isEmpty()) {
        // Afficher les résultats de la recherche dans la TableView
        tab_ev_liste.setItems(evenementsRecherche);
    } else {
        // Afficher un message d'erreur si aucune participation n'a été trouvée
        System.out.println("Aucune participation trouvée pour l'événement " + titreEvenement);
    }
}

@FXML
public void filterEvents() {
    LocalDate date_debut = date_deb.getValue();
    Date sqlDateDebut = Date.valueOf(date_debut);
    LocalDate date_fin = datefin.getValue();
    Date sqlDateFin = Date.valueOf(date_fin);

    EvenementService es = new EvenementService();
    ObservableList<Evenement> filteredList = es.getBetweenDates(sqlDateDebut, sqlDateFin);
    tab_ev_liste.setItems(filteredList);
}
    
   


    // ...







            
            
   /* btn_like.setOnAction(event -> {
            likeCount++;
            countLabel.setText("Likes: " + likeCount + " Dislikes: " + dislikeCount);
        });
        
        dislikeBtn.setOnAction(event -> {
            dislikeCount++;
            countLabel.setText("Likes: " + likeCount + " Dislikes: " + dislikeCount);
        });*/
}
