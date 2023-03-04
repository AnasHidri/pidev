/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiOffre;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Candidature;
import khademni.services.CandidatureService;

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
    private Button btnretourne;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine;

    @FXML
    private TableColumn<?, ?> colTitre;
 @FXML
    private TableColumn<?, ?> colEtat;

    @FXML
    private TableView<Candidature> tvMesCandidature;
    @FXML
    private TableView<Candidature> tvMesCandidature1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showOffre1();
         showOffre2();
    }    
    
         public void showOffre1(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Candidature> mesCandidatures = cs.afficherCandidatureClient1();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDateDebut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colDateLimite.setCellValueFactory(new PropertyValueFactory <>("date_limite"));


       tvMesCandidature.setItems(mesCandidatures);
}  
             public void showOffre2(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Candidature> mesCandidatures = cs.afficherCandidatureClient2();

colEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

       tvMesCandidature1.setItems(mesCandidatures);
}  
   
    @FXML
    void retourneOffre(ActionEvent event) throws IOException {
SceneController SC = new SceneController();
    SC.Scene5(event);
    }
}
