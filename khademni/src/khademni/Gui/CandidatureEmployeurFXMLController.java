/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private TableColumn<?, ?> ColEtat;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colPrenom;

    @FXML
    private TableColumn<?, ?> colTitre;

    @FXML
    private TableView<Candidature> tvCandidature1;

    @FXML
    private TableView<Candidature> tvCandidature2;

    @FXML
    private TableView<Candidature> tvCandidature3;

    @FXML
    private Button btnRefuser;

    @FXML
    private Button btnvalide;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        
       showCandidature1();
       showCandidature2();
       showCandidature3();
    }    
    
 public void showCandidature1(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Candidature> listeCandidature1 = cs.afficherCandidatureEmployeur1();

colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));

       tvCandidature1.setItems(listeCandidature1);
} 

  public void showCandidature2(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Candidature> listeCandidature2 = cs.afficherCandidatureEmployeur2();

colNom.setCellValueFactory(new PropertyValueFactory <>("nom"));
colPrenom.setCellValueFactory(new PropertyValueFactory <>("prenom"));
colEmail.setCellValueFactory(new PropertyValueFactory<> ("email"));    
       tvCandidature2.setItems(listeCandidature2);
} 
 public void showCandidature3(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Candidature> listeCandidature3 = cs.afficherCandidatureEmployeur3();

ColEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

       tvCandidature3.setItems(listeCandidature3);
} 
   @FXML
    void refusCandidat(ActionEvent event) {
     Candidature selectedCandid = tvCandidature3.getSelectionModel().getSelectedItem();
     Candidature c =new Candidature();
     CandidatureService CO= new CandidatureService();
    if (selectedCandid == null) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner un Candidature à Refusée !");
        alert.showAndWait();
        return;
          
    }CO.RefuserCandidature(selectedCandid);
  CO.AffichAttenteCandidature(c);
    
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Candidature Validée!");
    alert.showAndWait();
     tvCandidature3.refresh();
  showCandidature3();
    }
     
    @FXML
    void validerCandidat(ActionEvent event) {

    }

}