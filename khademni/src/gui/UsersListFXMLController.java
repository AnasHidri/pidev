/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Utilisateur;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UsersListFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Utilisateur> tableviewUser;
    
    @FXML
    private TableColumn<?, ?> nomUser;
    @FXML
    private TableColumn<?, ?> prenomUser;
    @FXML
    private TableColumn<?, ?> roleUser;
    @FXML
    private TableColumn<?, ?> etatUser;
    @FXML
    private TableColumn<?, ?> emailUser;
    
     @FXML
    private Button btn_modifier;
     
     @FXML
    private TextField Recherche_User;
   
    
    UtilisateurService us = new UtilisateurService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                showUsers();
    } 
    
     public void showUsers(){
       
         ObservableList<Utilisateur> list = us.afficherUtilisateurs() ;
         System.out.println("list ::: "+list);
         nomUser.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenomUser.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         roleUser.setCellValueFactory(new PropertyValueFactory<>("role"));
         etatUser.setCellValueFactory(new PropertyValueFactory<>("etat"));
         emailUser.setCellValueFactory(new PropertyValueFactory<>("mail"));
         
         tableviewUser.setItems(list);
         
     }
     
     @FXML
    private void ModifierUser(ActionEvent event) throws IOException {
        Utilisateur user = tableviewUser.getSelectionModel().getSelectedItem();
        try{
         // Charger la nouvelle vue
            System.out.println("user.id::::"+user.getId_user());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUserFXML.fxml"));
        Parent root = loader.load();
        
       // Obtenir le contrôleur de la nouvelle vue
        ModifierUserFXMLController controleur = loader.getController();
              
        // Passer les donnees de l'utilisateur actuel au nouveau controleur
        controleur.setTextFields(user);

        
         // Afficher la nouvelle vue dans la fenêtre principale
        Scene scene = new Scene(root);
        Stage stage = (Stage) btn_modifier.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        showUsers();

        }catch(IOException e){
            System.out.println(e.getCause().getMessage());
        }
       
    }
    
    @FXML
    private void SupprimerUtilisateur(ActionEvent event) {
        Utilisateur user = tableviewUser.getSelectionModel().getSelectedItem();
        us.supprimerUtilisateur(user);
        showUsers();
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur supprimé");
                alert.showAndWait();  

    }
    
    /*
    private void searchRec() {
         ObservableList<Utilisateur> list = us.afficherUtilisateurs() ;
         System.out.println("list ::: "+list);
         nomUser.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenomUser.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         roleUser.setCellValueFactory(new PropertyValueFactory<>("role"));
         etatUser.setCellValueFactory(new PropertyValueFactory<>("etat"));
         emailUser.setCellValueFactory(new PropertyValueFactory<>("mail"));
      
         tableviewUser.setItems(list);
         FilteredList<Utilisateur> filteredData = new FilteredList<>(list,b->true);
         Recherche_User.textProperty().addListener((observable,oldValue,newValue)-> {
             filteredData.setPredicate(rec-> {
                 if (newValue == null || newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter = newValue.toLowerCase();
                 if (rec.getMail().toLowerCase().contains(lowerCaseFilter)){
                 return true;
                 }else if (rec.getNom().toLowerCase().contains(lowerCaseFilter)){
                 return true;
                 }
                 else
                 return false ;
             });
         });
}
*/
}
