/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import api.MailService;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import khademni.entity.Formateur;
import khademni.entity.Utilisateur;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ActivationFormateurFXMLController implements Initializable {

    @FXML
    private TableView tableviewUser;
    @FXML
    private TableColumn nomUser;
    @FXML
    private TableColumn prenomUser;
    @FXML
    private TableColumn roleUser;
    @FXML
    private TableColumn etatUser;
    @FXML
    private TableColumn emailUser;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField Recherche_User;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                showUsers();
    }  
    
    UtilisateurService us = new UtilisateurService();
    
    
     @FXML
public void showUsers() {
    ObservableList<Utilisateur> list = us.afficherUtilisateurs();
    System.out.println("list ::: " + list);

    // Filter the list to only include Formateurs with inactive state
    list = list.filtered(u -> u.getRole().equals("Formateur") && u.getEtat().equals("inactif"));

    nomUser.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomUser.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    etatUser.setCellValueFactory(new PropertyValueFactory<>("etat"));
    emailUser.setCellValueFactory(new PropertyValueFactory<>("mail"));

    tableviewUser.setItems(list);
}
    public void SendMail(String destination, String subject, String body) throws GeneralSecurityException{        
    try {
          MailService.sendEmail(destination, subject,body);
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
    
         @FXML
    private void Activer(ActionEvent event) {
        Utilisateur user = (Utilisateur) tableviewUser.getSelectionModel().getSelectedItem();
             System.out.println("userrrr id :: "+user.getId_user());
        us.changeUserState(user.getId_user(), "actif");
        try {
                 SendMail(user.getMail(), "Khadamni, Activation du compte ", "Bonjour, votre compte est activé par l'admin, vous pouvez y accéder.");
             } catch (GeneralSecurityException ex) {
                 System.out.println(ex.getMessage());
             }
             System.out.println("c bon");
        showUsers();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("compte activé ");
                alert.showAndWait();  
        

    }
    
    private void searchRec() {
    ObservableList<Utilisateur> list = us.afficherUtilisateurs();
    FilteredList<Utilisateur> filteredData = new FilteredList<>(list, p -> true);

    // Set the items of your TableView to the filtered data
    tableviewUser.setItems(filteredData);

    // Add a listener to the text property of the TextField to detect changes in user input
    Recherche_User.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(user -> {
            // If the TextField is empty, show all items
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Convert the user input to lowercase
            String lowerCaseFilter = newValue.toLowerCase();

            // Define a Predicate to match the user input against the relevant columns in your TableView
            if (user.getNom().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Match on the nom column
            } else if (user.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Match on the prenom column
            } else if (user.getMail().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Match on the email column
            } else if (user.getRole().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Match on the role column
            } else {
                return false; // No match
            }
        });
    });
}
    
    
    @FXML
    public void logout(){
        try{
                                        // Charger la nouvelle vue
                                       FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionFXML.fxml"));
                                       Parent root = loader.load();
                                        // Afficher la nouvelle vue dans la fenêtre principale
                                       Scene scene = new Scene(root);
                                       Stage stage = (Stage) btn_modifier.getScene().getWindow();
                                       stage.setScene(scene);
                                       stage.show();
                                       
                                       Utilisateur.setCurrent_User(new Utilisateur());

                                       }catch(IOException ex){
                                           System.out.println(ex.getCause().getMessage());
                                       }
    }
}
