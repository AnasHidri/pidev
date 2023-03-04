/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiUser;

import khademni.api.MailService;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import khademni.entity.Utilisateur;
import khademni.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierUserFXMLController implements Initializable {

    
     @FXML
    private TextField tfnom2;
    @FXML
    private TextField tfprenom2;
    @FXML
    private TextField tfrole2;
    @FXML
    private TextField tfmail2;
    
    @FXML
    private TextField idUser2;
    
    @FXML
    private Button modifier_btn;
    
    @FXML
    private ChoiceBox cbetat;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }  
    
     public void SendMail(String destination, String subject, String body) throws GeneralSecurityException{        
    try {
                // MailService.sendEmail("ymahfoudh55@gmail.com", "test","bonjour");
                 MailService.sendEmail(destination, subject,body);
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
     
    
    @FXML
    void ModifierUser(ActionEvent event) throws IOException {
         UtilisateurService us= new UtilisateurService();
         
         Integer id=Integer.valueOf(idUser2.getText());
         System.out.println("idd :::: "+id);
         String nom=tfnom2.getText();
         String prenom=tfprenom2.getText();
         String role=tfrole2.getText();
         String etat=cbetat.getValue().toString();
         System.out.println("cbetat.getValue().toString()"+cbetat.getValue().toString());
         String mail=tfmail2.getText();
         String domaine ="";
         String password ="";
         
         Utilisateur user =new Utilisateur(id,nom,prenom,"",role,etat,mail,domaine,password);
           us.modifierUtilisateur(user);
           
           if("actif".equals(etat)){
             try {
                 SendMail(mail, "Khadamni, Activation du compte ", "Bonjour, votre compte est activé par l'admin, vous pouvez y accéder.");
             } catch (GeneralSecurityException ex) {
                 System.out.println(ex.getMessage());
             }
           }
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Utilsateur modifié");
                alert.showAndWait();  
                
                // Charger la nouvelle vue
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersListFXML.fxml"));
        Parent root = loader.load();

        
         // Afficher la nouvelle vue dans la fenêtre principale
        Scene scene = new Scene(root);
        Stage stage = (Stage) idUser2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    
      public void setTextFields(Utilisateur u){
        idUser2.setText(String.valueOf(u.getId_user()));
       tfnom2.setText(u.getNom());
        tfprenom2.setText(u.getPrenom());
        tfrole2.setText(u.getRole());
        tfmail2.setText(u.getMail());
    }
    
}
