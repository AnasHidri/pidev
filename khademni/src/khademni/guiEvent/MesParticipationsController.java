/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiEvent;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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

import khademni.entity.Evenement;
import khademni.entity.Participation;
import khademni.services.EvenementService;
import khademni.services.ParticipationService;
import khademni.api.PdfEv;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MesParticipationsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
     private TableView<Evenement> tab_mes_parti;
     @FXML
     private TableColumn titre_ma_liste;
     @FXML
     private TableColumn desc_ma_liste;
     @FXML
     private TableColumn nom_soc_ma_liste;
     @FXML
     private TableColumn lieu_ma_liste;
     @FXML
     private TableColumn date_deb_maliste;
     @FXML
     private TableColumn date_fin_maliste;
     @FXML
     private Button btn_select_ma_liste;
      @FXML
     private Button btn_annuler_parti_ma_liste;
      @FXML
    private TextField idev2;
      @FXML
      private Button btn_like;
      @FXML
      private Button btn_dislike;
      @FXML
      private TextField recherche_text_par;
      @FXML
      private Button btn_recherche;
      @FXML
      private TextField nb_like_dislike;
      @FXML
      private Button btn_nb_ld;
      @FXML
      private Button qr_code;
      @FXML
      private Button btn_pdf;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        MesParticipations();
        btn_pdf.setOnAction(event -> {
        try {
            generatePdfParticipation("evenement.pdf", tab_mes_parti);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    });
        
        
    }    
    
     @FXML
      public void MesParticipations(){
     EvenementService es= new EvenementService();
         ObservableList<Evenement> liste = es.MaListe();
         System.out.println("list ::: "+liste);
         titre_ma_liste.setCellValueFactory(new PropertyValueFactory<>("titre"));
         desc_ma_liste.setCellValueFactory(new PropertyValueFactory<>("description"));
         nom_soc_ma_liste.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
         lieu_ma_liste.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         date_deb_maliste.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         date_fin_maliste.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
      
         tab_mes_parti.setItems(liste);
      }
      
      @FXML
    private void SelectEV(ActionEvent event){
        Evenement selectedEV =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV);
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));

      
    }
  /*  @FXML
    private void SelectP(ActionEvent event){
        Participation selectedPa =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_p::"+selectedPa);
        idev2.setText(String.valueOf(selectedPa.getId_evenement()));

      
    }*/
    
     @FXML
    private void AnnulerP(ActionEvent event){
        Evenement selectedEV =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));
        System.out.println("tese1");
         ParticipationService ps= new ParticipationService();
         ps.supprimerParticipation(selectedEV);
        System.out.println("tese2");
        MesParticipations();
        
         // Envoi du mail
    String to = "oueslati.yassmine1@gmail.com"; // Adresse mail de l'utilisateur
    String from = "oueslati.yassmine1@gmail.com"; // Votre adresse mail
    String host = "smtp.gmail.com"; // Adresse du serveur SMTP (ici, Gmail)
    String username = "oueslati.yassmine1@gmail.com"; // Votre adresse mail
    String password = "hqtddpsdqejxjunk"; // Votre mot de passe Gmail
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
            private void LikeE(ActionEvent event){
                 Evenement selectedEV =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));
        Participation p= new Participation(selectedEV.getId_evenement(), 2,"active");
        System.out.println("tese1");
        ParticipationService ps= new ParticipationService();
        ps.likeEvent(p);
        ps.updateLikesAndDislikes(p);
        System.out.println("tese2");
            }
            
            @FXML
            private void DislikeE(ActionEvent event){
                 Evenement selectedEV =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));
         Participation p= new Participation(selectedEV.getId_evenement(), 2,"active");
        System.out.println("tese1");
        ParticipationService ps= new ParticipationService();
        ps.DislikeEvent(p);
        ps.updateLikesAndDislikes(p);
        System.out.println("tese2");
            }
            
 
            
            @FXML
        private void rechercheEvenement(ActionEvent event) {
        // Récupérer le titre de l'événement à rechercher depuis le champ de texte
         String titreEvenement = recherche_text_par.getText();

       
      // Récupérer la liste des événements auxquels l'utilisateur a participé depuis la TableView
        ObservableList<Evenement> evenements = tab_mes_parti.getItems();

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
        tab_mes_parti.setItems(evenementsRecherche);
      } else {
       // Afficher un message d'erreur si aucune participation n'a été trouvée
       System.out.println("Aucune participation trouvée pour l'événement " + titreEvenement);
              }
    }
        
  @FXML
 public void AfficherLikesDislikes(ActionEvent event) {
    // Récupérer l'ID de l'événement sélectionné
    Evenement selectedEV = tab_mes_parti.getSelectionModel().getSelectedItem();
    System.out.println("id_e::"+selectedEV.getId_evenement());
    idev2.setText(String.valueOf(selectedEV.getId_evenement()));

    // Récupérer l'instance de Participation pour l'événement sélectionné depuis votre base de données
    Participation p = new Participation(selectedEV.getId_evenement(), 2, "active");
    System.out.println("test1");

    // Afficher les résultats dans votre interface utilisateur
    ParticipationService ps = new ParticipationService();
    int[] likesAndDislikes = ps.getLikesAndDislikesCount(p);
    nb_like_dislike.setText("Likes: " + likesAndDislikes[0] + " Dislikes: " + likesAndDislikes[1]);

    // Ajouter un écouteur sur l'instance de Participation pour mettre à jour l'affichage des likes et dislikes automatiquement
    ps.addPropertyChangeListener(evt -> {
        int[] newLikesAndDislikes = ps.getLikesAndDislikesCount(p);
        Platform.runLater(() -> {
            nb_like_dislike.setText("Likes: " + newLikesAndDislikes[0] + " Dislikes: " + newLikesAndDislikes[1]);
        });
    });
}
 
     @FXML
    private void generer(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Qrcode.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
    
     @FXML
    private void Retour(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEvenement.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
    
    @FXML
public void generatePdfParticipation(String filename, TableView<Evenement> tableView) throws DocumentException, FileNotFoundException {
    Evenement selectedEV = tab_mes_parti.getSelectionModel().getSelectedItem();
    System.out.println("id_e::" + selectedEV.getId_evenement());
    if (selectedEV != null) {
        PdfEv pd = new PdfEv();
        try {
            ByteArrayOutputStream qrCodeStream = new ByteArrayOutputStream();
            String pdfFilePath = new File(filename).getAbsolutePath();
            pd.generateQRCode("file:///" + pdfFilePath, qrCodeStream);
            Image qrCodeImage = Image.getInstance(qrCodeStream.toByteArray());
            pd.generatePdfWithQRCode(filename, selectedEV, qrCodeImage);
            System.out.println("impression done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
     
     
}