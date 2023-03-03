/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Evenement;
import khademni.entity.Utilisateur;
import khademni.services.EvenementService;
import khademni.services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LesParticipationsADController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
     private TableColumn nom_part;
    @FXML
     private TableColumn prenom_part;
    @FXML
     private TableColumn mail_part;
     @FXML
     private TableView<Utilisateur> tab_part;
     @FXML
     private TextField idEvent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
    @FXML
public void Participants(int eventId) {
    ParticipationService ps = new ParticipationService();
    ObservableList<Utilisateur> liste = ps.Participants(eventId);
 
         
         System.out.println("list ::: "+liste);
         nom_part.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenom_part.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         mail_part.setCellValueFactory(new PropertyValueFactory<>("mail"));
         
      
         tab_part.setItems(liste);
}
      
      private int eventId;
      
     public void setEventId(int eventId) {
    this.eventId = eventId;
}
    public void setTextFields(int eventId){
        idEvent.setText(eventId+"");
        System.out.println("anas "+eventId);
       Participants(eventId);
    }
    
     @FXML
    private void Retour(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEvenementAD.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
}