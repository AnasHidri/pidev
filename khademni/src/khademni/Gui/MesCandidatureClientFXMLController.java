/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Offre;
import khademniService.CandidatureService;
import khademniService.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class MesCandidatureClientFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableColumn<?, ?> colAdresse;

    @FXML
    private TableColumn<?, ?> colDateDebut;

    @FXML
    private TableColumn<?, ?> colDateLimite;
 @FXML
    private TableColumn<?, ?> colId_Offre;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine;

    @FXML
    private TableColumn<?, ?> colTitre;
 @FXML
    private TableColumn<?, ?> colEtat;

    @FXML
    private TableView<String> tvOffre23;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showOffre();
    }    
    
         public void showOffre(){
  CandidatureService cs =new CandidatureService(); 

ObservableList<String> list12 = cs.afficherCandidatureClient();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDateDebut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colDateLimite.setCellValueFactory(new PropertyValueFactory <>("date_limite"));
colEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));
colId_Offre.setCellValueFactory(new PropertyValueFactory <>("id_offre"));

    
       tvOffre23.setItems(list12);
}  
   
}
