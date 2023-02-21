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
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Evenement;
import khademni.entity.Participation;
import khademni.services.EvenementService;
import khademni.services.ParticipationService;

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
     private TableView<Participation> tab_mes_parti;
     @FXML
     private TableColumn titre_ma_liste;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MesParticipations();
    }    
    
    @FXML
      public void MesParticipations(){
     ParticipationService ps= new ParticipationService();
         ObservableList<Participation> list = ps.MaListe();
         System.out.println("list ::: "+list);
         titre_ma_liste.setCellValueFactory(new PropertyValueFactory<>("titre"));
         tab_mes_parti.setItems(list);
      }
}
