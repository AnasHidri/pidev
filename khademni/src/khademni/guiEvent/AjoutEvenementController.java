/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Evenement;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
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
     @FXML
     private DatePicker date_deb_ev;
     @FXML
     private DatePicker date_fin_ev;
     @FXML
    private Button btn_ajout_ev;
     @FXML
    private Button btn_modif_ev;
     @FXML
     private Button btn_supp_ev;
     @FXML
     private Button select_ev;
      @FXML
     private Button cons_part_emp;
     @FXML
     private TableView<Evenement> tab_ev;
     @FXML
     private TableColumn<Evenement, String> titre_ev_aff;
     @FXML
     private TableColumn<Evenement, String> desc_ev_aff;
     @FXML
     private TableColumn<Evenement, String> nomsoc_ev_aff;
     @FXML
     private TableColumn<Evenement, String> lieu_ev_aff;
      @FXML
     private TableColumn<Evenement, LocalDate> date_deb_aff;
       @FXML
     private TableColumn<Evenement, LocalDate> date_fin_aff;
          @FXML
       private ComboBox<String> liste_ev;
    @FXML
            private ComboBox<String> liste_off;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         liste_ev.getItems().addAll("Ajout evenement","Les Participants");
            liste_off.getItems().addAll("Les candidatures","Ajout offre");
       AffichEV(); 


liste_ev.setOnAction(event -> {
    String selectedPage = (String) liste_ev.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Ajout evenement")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/AjoutEvenement.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Les Participants")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/LesParticipantsEM.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

}) ;

liste_off.setOnAction(event -> {
    String selectedPage = (String) liste_off.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Les candidatures")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/CandidatureEmployeurFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Ajout offre")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/EmployeurFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

});

       
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
        
            else if (!titre_event.getText().matches("[a-zA-Z]+")) {
        // Afficher un message d'erreur si le titre contient des chiffres
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le titre ne doit contenir que des lettres");
        alert.showAndWait();
        }
        else{
        String titre=titre_event.getText();
        String description=desc_event.getText();
        String nom_societe=nom_soc.getText();
        String lieu=lieu_event.getText();
       LocalDate date_debut = date_deb_ev.getValue();
        LocalDate date_fin = date_fin_ev.getValue();

        // Vérifier si la date de début est supérieure à la date de fin
        if (date_debut.isAfter(date_fin)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("La date de début ne peut pas être supérieure à la date de fin");
            alert.showAndWait();
        } else {
            Date sqlDateDebut = Date.valueOf(date_debut);
            Date sqlDateFin = Date.valueOf(date_fin);
            System.out.println("tese1");
            Evenement e = new Evenement(10, sqlDateDebut, sqlDateFin, titre, description, nom_societe, lieu);
            EvenementService es = new EvenementService();
            es.ajouterEvenement(e);
            AffichEV();
            System.out.println("tese2");
        }
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
        date_deb_ev.setValue(selectedEV.getDate_debut().toLocalDate()); 
        date_fin_ev.setValue(selectedEV.getDate_debut().toLocalDate());
        
    }
    
     @FXML
    private void ModifEv(ActionEvent event) {
                int idev=Integer.parseInt(idev2.getText());

        String titre=titre_event.getText();
        String description=desc_event.getText();
        String nom_societe=nom_soc.getText();
        String lieu=lieu_event.getText();
        LocalDate date_debut = date_deb_ev.getValue();
       Date sqlDateDebut = Date.valueOf(date_debut);
      LocalDate date_fin = date_fin_ev.getValue();
       Date sqlDateFin = Date.valueOf(date_fin);
         System.out.println("tese1");
        Evenement e= new Evenement(idev,1,sqlDateDebut,sqlDateFin,titre, description, nom_societe, lieu);
        EvenementService es= new EvenementService();
        es.modifierEvenement(sqlDateDebut,sqlDateFin,titre, description, lieu, e);
        AffichEV();
                 System.out.println("tese2");

    }
   @FXML
public void AffichEV(){
       
     
    EvenementService es = new EvenementService();
    ObservableList<Evenement> list = es.getAllById();
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    titre_ev_aff.setCellValueFactory(new PropertyValueFactory<>("titre"));
    desc_ev_aff.setCellValueFactory(new PropertyValueFactory<>("description"));
    nomsoc_ev_aff.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
    lieu_ev_aff.setCellValueFactory(new PropertyValueFactory<>("lieu"));

    date_deb_aff.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
    date_fin_aff.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
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
    
      
    @FXML
public void ConsulterParticipants(ActionEvent event) throws IOException {
Evenement selectedEV = tab_ev.getSelectionModel().getSelectedItem();
int eventId = selectedEV.getId_evenement(); // Récupérer l'identifiant de l'événement sélectionné
System.out.println("id_e::" + eventId);

try {
// Charger la nouvelle vue
FXMLLoader loader = new FXMLLoader(getClass().getResource("LesParticipantsEM.fxml"));
Parent root = loader.load();


    // Obtenir le contrôleur de la nouvelle vue
    LesParticipantsEMController controleur = loader.getController();

    // Passer les données de l'utilisateur actuel et l'identifiant de l'événement sélectionné au nouveau contrôleur
    controleur.setTextFields(eventId);
  

    Scene scene = new Scene(root);
    Stage stage = (Stage) cons_part_emp.getScene().getWindow();
    stage.setScene(scene);
    stage.show();

} catch (IOException e) {
    System.out.println(e.getCause().getMessage());
}
    }
    @FXML
    private void Profile(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiUser/ProfileSettingsFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
    
}