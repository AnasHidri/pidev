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
import khademniService.CandidatureService;


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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        
        showOffre1();
        showOffre2();
        showOffre3();
    }    
    
 public void showOffre1(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Candidature> listeCandidature1 = cs.afficherCandidatureEmployeur1();

colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));

       tvCandidature1.setItems(listeCandidature1);
} 

  public void showOffre2(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Candidature> listeCandidature2 = cs.afficherCandidatureEmployeur2();

colNom.setCellValueFactory(new PropertyValueFactory <>("nom"));
colPrenom.setCellValueFactory(new PropertyValueFactory <>("prenom"));
colEmail.setCellValueFactory(new PropertyValueFactory <>("email"));
    
       tvCandidature2.setItems(listeCandidature2);
} 
 public void showOffre3(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Candidature> listeCandidature3 = cs.afficherCandidatureEmployeur3();

ColEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

       tvCandidature3.setItems(listeCandidature3);
} 
}