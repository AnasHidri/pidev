    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiOffre;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Candidature;
import khademni.entity.Offre;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
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
    private TableView<Offre> tvMesCandidature;
    @FXML
    private TableView<Candidature> tvMesCandidature1;
                @FXML
      private ComboBox<String> liste_for;
    
    @FXML
       private ComboBox<String> liste_ev;
    @FXML
            private ComboBox<String> liste_off;
    @FXML
            private ComboBox<String> pani;
    @FXML
            private Button prof;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showOffre1();
         showOffre2();
         
          liste_for.getItems().addAll("Liste formation");
            liste_ev.getItems().addAll("Liste evenement","Mes participations");
            liste_off.getItems().addAll("Liste offre","Mes candidatures");
            pani.getItems().addAll("Mon panier","Mes formations");
            
            
            liste_for.setOnAction(event -> {
    String selectedPage = (String) liste_for.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste formation")) {
        // navigate to Page 1
    } else if (selectedPage.equals("Mes formations")) {
        // navigate to Page 2
    } 
});

liste_ev.setOnAction(event -> {
    String selectedPage = (String) liste_ev.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste evenement")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/ListeEvenement.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Mes participations")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/MesParticipations.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});

liste_off.setOnAction(event -> {
    String selectedPage = (String) liste_off.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste offre")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/ListOffreClientFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Mes candidatures")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/MesCandidatureClientFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});

pani.setOnAction(event -> {
    String selectedPage = (String) pani.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Mon panier")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/gui/PanierFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Mes formations")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/gui/MesFormationFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});
    }    
    
         public void showOffre1(){
  CandidatureService cs =new CandidatureService(); 
ObservableList<Offre> mesCandidatures = cs.afficherOffreClient1();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDateDebut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));


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
