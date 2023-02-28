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

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatEvADController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
     private TableView<Evenement> tab_stat;
     @FXML
     private TableColumn titre_best;
      @FXML
     private TableColumn nom_soc_best;
      @FXML
    private TextField idevb;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListEV();
    }    
    
    
     @FXML
    public void ListEV(){
       
         EvenementService es= new EvenementService();
         ObservableList<Evenement> list = es.Stat();
         System.out.println("list ::: "+list);
         //Evenement e= new Evenement(35,titre, nom_soc);
        // idevb.setText(String.valueOf(e.getId_evenement()));
         titre_best.setCellValueFactory(new PropertyValueFactory<>("titre"));
         
         nom_soc_best.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
        
      
         tab_stat.setItems(list);
         
     }
    
}
