/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import khademni.entity.Utilisateur;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProfileSettingsFXMLController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfdomaine;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfactuel;
    @FXML
    private TextField tfnouv;
    @FXML
    private TextField tfconfirm;
     @FXML
    private TextField tfimg;
    
    @FXML
    private Label login;
    
    @FXML
    private Button modifier_btn;
     @FXML
    private ImageView imageView;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTextFields();
        displayImage();
    //    Image image = new Image("C:\\Users\\ASUS\\Desktop\\IMG_1976.png");
   // imageView.setImage(image);
    }   
    
    UtilisateurService us = new UtilisateurService();
    
     public void setTextFields(){
        tfnom.setText(Utilisateur.Current_User.getNom());
        tfprenom.setText(Utilisateur.Current_User.getPrenom());
        tfdomaine.setText(Utilisateur.Current_User.getDomaine());
        tfmail.setText(Utilisateur.Current_User.getMail());
        login.setText(Utilisateur.Current_User.getLogin());
        tfimg.setText(Utilisateur.Current_User.getImage());
    }
    
     
     @FXML
    void selectImage(ActionEvent event) throws FileNotFoundException, SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String imagePath = selectedFile.getPath();
            // Save the imagePath in your database using an SQL query
            tfimg.setText(imagePath);
            us.updateImage(imagePath, Utilisateur.Current_User.getId_user());
            // Display the selected image in the ImageView control
            Image image = new Image(new FileInputStream(selectedFile));
            imageView.setImage(image);
        }
    }
    
     public void displayImage() {
    try {
        Image image = new Image(new FileInputStream(tfimg.getText()));
        imageView.setImage(image);
    } catch (FileNotFoundException e) {
        System.out.println("Image file not found: " + tfimg.getText());
    }
}
     
     @FXML
    void ModifierUser(ActionEvent event) throws IOException {
         UtilisateurService us= new UtilisateurService();
         
         Integer id=Utilisateur.Current_User.getId_user();
         System.out.println("idd :::: "+id);
         String nom=tfnom.getText();
         String prenom=tfprenom.getText();
         String domaine=tfdomaine.getText();
         String mail=tfmail.getText();
         String mdp=tfnouv.getText();
         
         String role="";
         String etat="";
         
         if(Utilisateur.Current_User.getPassword().equals(tfactuel.getText()) && tfnouv.getText().equals(tfconfirm.getText())){
             
         
        
         
         Utilisateur user =new Utilisateur(id,nom,prenom,role,etat,mail,domaine,mdp);
         System.out.println(user.getDomaine());
         System.out.println("user :: "+user); 
           us.modifierProfil(user);
           
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Données modifiés");
                alert.showAndWait();  
         }else {
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Erreur au niveau du mot de passe");
                alert.showAndWait();  
         }
  
             

    }
     
}