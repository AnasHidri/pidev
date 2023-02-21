/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.net.URL;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Evenement;
import khademni.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutEvenementController implements Initializable {
 /**
     * Initializes the controller class.
     */
    
     @FXML
    private TextField titre_event;
     @FXML
    private TextField desc_event;
      @FXML
    private TextField nom_soc;
       @FXML
    private TextField lieu_event;
       @FXML
    private TextField idev2;
   //  @FXML
     //private DatePicker date_deb_ev;
     //@FXML
     //private DatePicker date_fin_ev;
     @FXML
    private Button btn_ajout_ev;
     @FXML
    private Button btn_modif_ev;
     @FXML
     private Button btn_supp_ev;
     @FXML
     private Button select_ev;
     @FXML
     private TableView<Evenement> tab_ev;
     @FXML
     private TableColumn titre_ev_aff;
     @FXML
     private TableColumn desc_ev_aff;
     @FXML
     private TableColumn nomsoc_ev_aff;
     @FXML
     private TableColumn lieu_ev_aff;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       AffichEV();        
    }    
     
    
     @FXML
    private void AjoutEv(ActionEvent event) {
        if (titre_event.getText().isEmpty() || desc_event.getText().isEmpty()|| nom_soc.getText().isEmpty() || lieu_event.getText().isEmpty() ) {
        // Afficher un message d'erreur si un champ est vide
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Tous les champs sont obligatoires");
        alert.showAndWait();
        }
        
            else if (titre_event.getText().matches(".*\\d.*")) {
        // Afficher un message d'erreur si le titre contient des chiffres
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le titre ne doit pas contenir de chiffres");
        alert.showAndWait();
        }
        else{
        String titre=titre_event.getText();
        String description=desc_event.getText();
        String nom_societe=nom_soc.getText();
        String lieu=lieu_event.getText();
       // DatePicker datePicker = new DatePicker();
     
            // Get the selected date from the DatePicker control
            //LocalDate date_deb_ev = datePicker.getValue();
           // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           // String date_debut = date_deb_ev.format(dateFormatter);
            
        //String date_debut=date_deb_ev.getText();
        //String date_fin=date_fin_ev.getText();
         System.out.println("tese1");
        Evenement e= new Evenement(1,titre, description, nom_societe, lieu);
        EvenementService es= new EvenementService();
        es.ajouterEvenement(e);
        AffichEV(); 
                 System.out.println("tese2");
                }

    }
    
    @FXML
    private void SelectEV(ActionEvent event){
        Evenement selectedEV =  tab_ev.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));

        titre_event.setText(selectedEV.getTitre());
        desc_event.setText(selectedEV.getDescription());
        nom_soc.setText(selectedEV.getNom_societe());
        lieu_event.setText(selectedEV.getLieu());
    }
    
     @FXML
    private void ModifEv(ActionEvent event) {
                int idev=Integer.parseInt(idev2.getText());

        String titre=titre_event.getText();
        String description=desc_event.getText();
        String nom_societe=nom_soc.getText();
        String lieu=lieu_event.getText();
        //String date_debut=date_deb_ev.getText();
        //String date_fin=date_fin_ev.getText();
         System.out.println("tese1");
        Evenement e= new Evenement(idev,1,titre, description, nom_societe, lieu);
        EvenementService es= new EvenementService();
        es.modifierEvenement(titre, description, lieu, e);
        AffichEV();
                 System.out.println("tese2");

    }
    @FXML
    public void AffichEV(){
       
         EvenementService es= new EvenementService();
         ObservableList<Evenement> list = es.getAll();
         System.out.println("list ::: "+list);
         titre_ev_aff.setCellValueFactory(new PropertyValueFactory<>("titre"));
         desc_ev_aff.setCellValueFactory(new PropertyValueFactory<>("description"));
         nomsoc_ev_aff.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
         lieu_ev_aff.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         
      
         tab_ev.setItems(list);
         
     }
    
    @FXML
    private void SuppEv(ActionEvent event){
        Evenement selectedEV =  tab_ev.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        EvenementService es= new EvenementService();
        es.supprimerEvenement(selectedEV);
        AffichEV();
        
    }
    
    
    
}
