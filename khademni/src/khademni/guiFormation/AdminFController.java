/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiFormation;



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
import khademni.entity.Formation;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
import khademni.services.FormationService;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class AdminFController implements Initializable {

    @FXML
    private TableView<Formation> table;
    @FXML
    private TableColumn<Formation, String> titreclm;
    @FXML
    private TableColumn<Formation, String> dmnclm;
    @FXML
    private TableColumn<Formation, Double> prclm;
    @FXML
    private TableColumn<Formation, String> desclm;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          liste_for.getItems().addAll("Liste formation");
            liste_ev.getItems().addAll("Liste evenement");
            liste_off.getItems().addAll("Liste offre");
            pani.getItems().addAll("Liste user", "Liste activation");
          
            
            
           liste_for.setOnAction(event -> {
    String selectedPage = (String) liste_for.getSelectionModel().getSelectedItem();

  if (selectedPage.equals("Liste formation")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiFormation/AdminF.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});

liste_ev.setOnAction(event -> {
    String selectedPage = (String) liste_ev.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste evenement")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/ListeEvenementAD.fxml";
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
       String ch= "/khademni/guiOffre/OffreAdminFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});

pani.setOnAction(event -> {
    String selectedPage = (String) pani.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste user")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiUser/UsersListFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Liste activation")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiUser/ActivationFormateurFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});
        
        
        loadFormations();}
    FormationService fs = new FormationService() ;
    
    
    

        
        private void loadFormations() {

        ObservableList<Formation> listef = fs.afficherFormation();
        titreclm.setCellValueFactory(new PropertyValueFactory<>("titre"));
        dmnclm.setCellValueFactory(new PropertyValueFactory<>("domaine_formation"));
        prclm.setCellValueFactory(new PropertyValueFactory<>("prix"));
        desclm.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.setItems(listef);
    
    }  
         
        @FXML
    private void Profile(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/gui/HistoriqueFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
    
          @FXML
    private void stat(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiUser/DashboardFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
    
}