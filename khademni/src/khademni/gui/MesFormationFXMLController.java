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
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;
import khademni.services.Ligne_CommandeService;

/**
 * FXML Controller class
 *
 * @author mikea
 */
public class MesFormationFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Ligne_commande> tableviewUser;
    @FXML
    private TableColumn<?, ?> titreform;
    @FXML
    private TableColumn<?, ?> description;
    
        Ligne_CommandeService us = new Ligne_CommandeService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRec();
    }    
    
     @FXML
    public void showRec(){
       
         ObservableList<Ligne_commande> list = us.afficherMesFormation() ;
         System.out.println("list ::: "+list);
         titreform.setCellValueFactory(new PropertyValueFactory<>("titre"));
         description.setCellValueFactory(new PropertyValueFactory<>("prix"));
      
         tableviewUser.setItems(list);
         
      
         
         
     }
    
}
