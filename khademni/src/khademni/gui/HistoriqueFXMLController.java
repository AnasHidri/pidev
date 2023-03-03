/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Historique;
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;
import khademni.services.HistoriqueService;


/**
 * FXML Controller class
 *
 * @author mikea
 */
public class HistoriqueFXMLController implements Initializable {

       @FXML
        private TableView<Historique> tableviewUser;
              @FXML
        private TableView<String> tableviewUser2;
    @FXML
    private TableColumn<?, ?> dateaction;
        @FXML
    private TableColumn<?, ?> action;
              @FXML
    private TableColumn<String, String> username;
         @FXML 
     private TextField recherche;
    
    HistoriqueService hs= new HistoriqueService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        showRec();
        
          recherche.textProperty().addListener((observable, oldValue, newValue) -> {
        // Call the chercher method with the updated search term
        chercher(null);
    });
        // TODO
    }  
    
     @FXML
    public void showRec(){
       
         ObservableList<Historique> list = hs.afficherHistorique() ;
         System.out.println("list ::: "+list);
         dateaction.setCellValueFactory(new PropertyValueFactory<>("date_action"));
         action.setCellValueFactory(new PropertyValueFactory<>("action"));
    
           tableviewUser.setItems(list);

                    ObservableList<String> list2 = hs.afficherHistoriqueWithUser() ;
           

        username.setCellValueFactory(cellData -> {
             // Return the value from the observable list at the row index of the cell
             int rowIndex = tableviewUser2.getItems().indexOf(cellData.getValue());
             return new SimpleStringProperty(list2.get(rowIndex));
         });
        
        // Set the items for the table view to the observable list
        tableviewUser2.setItems(list2);
         
         
     }
    
     public void chercher (ActionEvent event){
     //to be continued  
     
     }
    
}
