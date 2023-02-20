/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import khademni.entity.Utilisateur;
import services.UtilisateurService;

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
    private TextField tfetat2;
    @FXML
    private TextField idUser2;
    
    @FXML
    private Button modifier_btn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    void ModifierUser(ActionEvent event) throws IOException {
         UtilisateurService us= new UtilisateurService();
         
         Integer id=Integer.valueOf(idUser2.getText());
         System.out.println("idd :::: "+id);
         String nom=tfnom2.getText();
         String prenom=tfprenom2.getText();
         String role=tfrole2.getText();
         String etat=tfetat2.getText();
         String mail=tfmail2.getText();
         
         Utilisateur user =new Utilisateur(id,nom,prenom,role,etat,mail);
           us.modifierUtilisateur(user);
           
           
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
        tfetat2.setText(u.getEtat());
        tfmail2.setText(u.getMail());
    }
    
}
