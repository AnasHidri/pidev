/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private Button btnRefuser;
    @FXML
    private Button btnRetourne;
  @FXML
    private Button btnvalide;
  @FXML
    private TextField tfSearch;
    @FXML
    private DatePicker tfDate_Debut;

    @FXML
    private DatePicker tfDate_limite;
 @FXML
    private TableColumn<?, ?> colEtat;

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
    private Button btnafficher;
   
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
    @FXML
    void Search(ActionEvent event) {

  OffreService os =new OffreService(); 
        FilteredList<Offre> filter = new FilteredList<>(os.afficherOffre(), e -> true);

        tfSearch.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateOffreData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateOffreData.getTitre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateOffreData.getDomaine_offre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateOffreData.getAdresse_societe().toLowerCase().contains(searchKey)) {
                    return true;
               
                } else 
                    return false;
                
            });
        });

        SortedList<Offre> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(tvOffre.comparatorProperty());
        tvOffre.setItems(sortList);
    
    }

    public void showOffre(){
           OffreService os =new OffreService(); 

ObservableList<Offre> list = os.afficherattenteOffre();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse_societe.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine_offre.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDate_debut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colDate_limite.setCellValueFactory(new PropertyValueFactory <>("date_limite"));
    colEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

       tvOffre.setItems(list);
}
    

    @FXML
    void toutOffres(ActionEvent event) {
  OffreService os =new OffreService(); 

ObservableList<Offre> list = os.afficherattenteOffre();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse_societe.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine_offre.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDate_debut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colDate_limite.setCellValueFactory(new PropertyValueFactory <>("date_limite"));
    colEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

       tvOffre.setItems(list);

    }

    @FXML
    void retourneInter(ActionEvent event) throws IOException {
         SceneController SC= new SceneController();
         SC.Scene4(event);
    }
    
    @FXML
    void RefuserOffre(ActionEvent event) {
     Offre selectedOffre = tvOffre.getSelectionModel().getSelectedItem();
     Offre o =new Offre();
  OffreService os= new OffreService();
    if (selectedOffre == null) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une Offre à Refusée !");
        alert.showAndWait();
        return;
          
    }os.RefuseOffre(selectedOffre);
    os.afficherattenteOffre();
    
   
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Offre Refusée!");
    alert.showAndWait();
     tvOffre.refresh();
  showOffre();

}    
        
 
    
    
       @FXML
    void validerOffre(ActionEvent event) { 
         
     Offre selectedOffre = tvOffre.getSelectionModel().getSelectedItem();
     Offre o =new Offre();
  OffreService os= new OffreService();
    if (selectedOffre == null) {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une Offre à Validée !");
        alert.showAndWait();
        return;
          
    }os.accepterOffre(selectedOffre);
    os.afficherattenteOffre();
    
   
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("Offre Validée!");
    alert.showAndWait();
     tvOffre.refresh();
  showOffre();

    
}@FXML
void filtrerOffre(ActionEvent event) {
    LocalDate date_debut = tfDate_Debut.getValue();
    LocalDate date_limite = tfDate_limite.getValue();
    
    Date datedeb = Date.valueOf(date_debut);
    Date datelim = Date.valueOf(date_limite);
    
    OffreService os = new OffreService(); 
    ObservableList<Offre> filteredList = os.getOffresByDate(
        datedeb.toLocalDate(),
        datelim.toLocalDate()
    );
    
    tvOffre.setItems(filteredList);
}
    
}
 









