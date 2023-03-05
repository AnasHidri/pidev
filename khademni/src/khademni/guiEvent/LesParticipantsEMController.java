/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Utilisateur;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
import khademni.services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LesParticipantsEMController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
     private TableColumn nom_part_em;
    @FXML
     private TableColumn prenom_part_em;
    @FXML
     private TableColumn mail_part_em;
     @FXML
     private TableView<Utilisateur> tab_part_em;
     @FXML
     private TextField ideve;
               @FXML
       private ComboBox<String> liste_ev;
    @FXML
            private ComboBox<String> liste_off;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          liste_ev.getItems().addAll("Ajout evenement","Les Participants");
            liste_off.getItems().addAll("Les candidatures","Ajout offre");
            
liste_ev.setOnAction(event -> {
    String selectedPage = (String) liste_ev.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Ajout evenement")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/AjoutEvenement.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Les Participants")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/LesParticipantsEM.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

}) ;

liste_off.setOnAction(event -> {
    String selectedPage = (String) liste_off.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Les candidatures")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/CandidatureEmployeurFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Ajout offre")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/EmployeurFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

});
    }    
    
    @FXML
public void Participants(int eventId) {
    ParticipationService ps = new ParticipationService();
    ObservableList<Utilisateur> liste = ps.Participants(eventId);
 
         
         System.out.println("list ::: "+liste);
         nom_part_em.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenom_part_em.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         mail_part_em.setCellValueFactory(new PropertyValueFactory<>("mail"));
         
      
         tab_part_em.setItems(liste);
}
    
     private int eventId;
      
     public void setEventId(int eventId) {
    this.eventId = eventId;
}
    public void setTextFields(int eventId){
        ideve.setText(eventId+"");
        System.out.println("anas "+eventId);
       Participants(eventId);
    }
    
    
     @FXML
    private void Retour(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutEvenement.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
    @FXML
    private void Profile(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiUser/ProfileSettingsFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
}
