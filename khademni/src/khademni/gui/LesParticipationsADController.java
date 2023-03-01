/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Evenement;
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
     private TableView tab_part;
     @FXML
     private TextField idEvent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
    @FXML
      public void MesParticipations(int eventId){
     ParticipationService ps= new ParticipationService();
         ObservableList<String>  liste = ps.Participants(eventId);
         System.out.println("list ::: "+liste);
         nom_part.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenom_part.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         
      
         tab_part.setItems(liste);
      }
      
      private int eventId;
      
     public void setEventId(int eventId) {
    this.eventId = eventId;
}
    public void setTextFields(int eventId){
        idEvent.setText(eventId+"");
        System.out.println("anas "+eventId);
       MesParticipations(eventId);
    }
     
}
