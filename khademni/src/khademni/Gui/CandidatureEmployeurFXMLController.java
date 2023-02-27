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
import khademni.entity.Candidature;
import khademni.entity.Offre;
import khademniService.CandidatureService;
import khademniService.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class CandidatureEmployeurFXMLController implements Initializable {

 @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEtat;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colTitre;

    @FXML
    private TableView<String> tvCandidature;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
 public void showOffre(){
  CandidatureService cs =new CandidatureService(); 

ObservableList<String> listeCandidature = cs.afficherCandidature();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colNom.setCellValueFactory(new PropertyValueFactory <>("nom"));
colPrenom.setCellValueFactory(new PropertyValueFactory <>("prenom"));
colEmail.setCellValueFactory(new PropertyValueFactory <>("email"));
colEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

    
       tvCandidature.setItems(listeCandidature);
} 


}