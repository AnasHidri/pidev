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
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Offre;
import khademniService.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class EmployeurFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */

  @FXML
    private Button btnAdd;

    @FXML
    private Button btnIntPr;
    @FXML
    private Button btnDelete;
  
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnListe;

    @FXML
    private TableColumn<?, ?> colAdresse_societe;

    @FXML
    private TableColumn<?, ?> colDate_debut;

    @FXML
    private TableColumn<?, ?> colDate_limite;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine_offre;

        @FXML
    private TableColumn<?, ?> colEtat;

    @FXML
    private TableColumn<?, ?> colTitre;
    

    @FXML
    private TextField tfAdressse_societe;

    @FXML
    private DatePicker tfDate_Limite;

    @FXML
    private DatePicker tfDate_debut;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfDomaine_offre;

    @FXML
    private TextField tfTitre;

    @FXML
    private TableView<Offre> tvOffre;
   

 
    @FXML
    private TextField tfRecherche;
    @FXML
    private TextField tfEtat;
    

     @Override
    public void initialize(URL location, ResourceBundle resources) {
        showOffre();
        
    }
    
    
    @FXML
    void RechercheOffre(ActionEvent event) {


  OffreService os =new OffreService(); 
        FilteredList<Offre> filter = new FilteredList<>(os.afficherOffre(), e -> true);

        tfRecherche.textProperty().addListener((Observable, oldValue, newValue) -> {

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
              /*  } else if (predicateOffreData.getDate_debut().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateOffreData.getDate_limite().toLowerCase().contains(searchKey)) {
                    return true;*/
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

ObservableList<Offre> list1 = os.afficherOffre();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse_societe.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine_offre.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDate_debut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colDate_limite.setCellValueFactory(new PropertyValueFactory <>("date_limite"));
colEtat.setCellValueFactory(new PropertyValueFactory <>("etat"));

    
       tvOffre.setItems(list1);
}  
   @FXML
    public void AddOffre(ActionEvent event) {
        Offre o;
        OffreService os=new OffreService();
     
    // if(event.getSource() == btnAdd){
                

       String titre= tfTitre.getText();
      String des= tfDescription.getText(); 
     String soc= tfAdressse_societe.getText(); 
     String off=  tfDomaine_offre.getText();
     LocalDate date_debut =tfDate_debut.getValue();
      Date datedeb= Date.valueOf(date_debut);
        LocalDate date_limite =tfDate_Limite.getValue();
      Date datelim= Date.valueOf(date_limite);

        o =new Offre(2, titre, des, soc, off, datedeb, datelim);
        
    if(titre.trim().isEmpty() || des.trim().isEmpty() || soc.trim().isEmpty()|| off.trim().isEmpty()) {
      System.out.println();
       Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Tous les champs doivent être remplis");
    alert.showAndWait();
    } else {
         os.ajouterOffre(o);
 Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Offre Ajoutée");
    alert.showAndWait();    }
             
  tvOffre.refresh();
  showOffre();

   
   
    }
 
   
    @FXML
 public void DeleteOffre(ActionEvent event) {
        OffreService os=new OffreService();
           Offre of = tvOffre.getSelectionModel().getSelectedItem();
           System.out.println(of.getId_offre());
       os.supprimerOffre(of);
         Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Offre Supprimée!");
    alert.showAndWait();
   
      os.afficherOffre();
      
        tvOffre.refresh();
  showOffre();

     
    }
    @FXML
    void IntP(ActionEvent event) throws IOException {
  SceneController SC= new SceneController();
         SC.Scene4(event);
    }
       @FXML
    void update(ActionEvent event) throws IOException {
       Offre selectedOffre = tvOffre.getSelectionModel().getSelectedItem();

    if (selectedOffre == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une Offre à modifier !");
        alert.showAndWait();
        return;
    }

    
    String titre = tfTitre.getText();  
    String description = tfDescription.getText();
    String adresse_societe = tfAdressse_societe.getText();
    String domaine_offre = tfDomaine_offre.getText(); 
    LocalDate date_debut =tfDate_debut.getValue();
    Date datedeb= Date.valueOf(date_debut);
    LocalDate date_limite =tfDate_Limite.getValue();
    Date datelim= Date.valueOf(date_limite);
    selectedOffre.setTitre(titre);
    selectedOffre.setDescription(description);
    selectedOffre.setAdresse_societe(adresse_societe);
    selectedOffre.setDomaine_offre(domaine_offre);
    selectedOffre.setDate_debut(datedeb);
    selectedOffre.setDate_limite(datelim); 
     
      
  
    System.out.println(selectedOffre); 
    OffreService OS = new OffreService(); 
    OS.modifierOffre(selectedOffre);
    System.out.println(selectedOffre); 
   
    tvOffre.refresh();

    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setContentText("Offre Modifié!");
    alert.showAndWait();
      tvOffre.refresh();
  showOffre();

    } 

      @FXML
    void ListeCandidature(ActionEvent event) throws IOException {
  SceneController SC= new SceneController();
         SC.Scene7(event);
    }
}

