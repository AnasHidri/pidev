/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class OffreAdminFXMLController implements Initializable {


  

 @FXML
    private TableColumn<?, ?> ColEtat;

    @FXML
    private TableColumn<?, ?> ColOffre;

    @FXML
    private TableColumn<?, ?> ColUser;

    @FXML
    private TableColumn<?, ?> colAdresse_societe;

    @FXML
    private TableColumn<?, ?> colCandid;

    @FXML
    private TableColumn<?, ?> colDate_debut;

    @FXML
    private TableColumn<?, ?> colDate_limite;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine_offre;

    @FXML
    private TableColumn<?, ?> colId_offre;

    @FXML
    private TableColumn<?, ?> colId_user;

    @FXML
    private TableColumn<?, ?> colTitre;

    @FXML
    private TableView<Offre> tvOffre;

    @FXML
    private TableView<Candidature> tvOffre1;
   
    //void handButtonAction(ActionEvent event) {
      /*if(event.getSource() == btnAdd){
            ajouterOffre();
        }
    */
    //}
    

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showOffre();
        

    }    

    public void showOffre(){
           OffreService os =new OffreService(); 

ObservableList<Offre> list = os.afficherOffre();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse_societe.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine_offre.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDate_debut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colDate_limite.setCellValueFactory(new PropertyValueFactory <>("date_limite"));
    
       tvOffre.setItems(list);


  
    
}
 }







