/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiFormation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Formation;
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
import khademni.services.FormationService;
import khademni.services.Ligne_CommandeService;
import khademni.services.PanierService;
import khademni.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class FormationCController implements Initializable {

    @FXML
    private TableColumn<Formation, String> titreclm;
    @FXML
    private TableColumn<Formation, String> dmnclm;
    @FXML
    private TableColumn<Formation, Double> prclm;
    @FXML
    private TableColumn<Formation, String> desclm;
    @FXML
    private TextField txtrech;
    @FXML
    private TableView<Formation> table;
    @FXML
    private Button btnpanier;
    @FXML
    private TextField id;
    
    private ObservableList<Formation> formationList;

    @FXML
    private Button btnCours;
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
          liste_for.getItems().addAll("Liste formation","Mes formations");
            liste_ev.getItems().addAll("Liste evenement","Mes participations");
            liste_off.getItems().addAll("Liste offre","Mes candidatures");
            pani.getItems().addAll("Mon panier");
    


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
    } 
});

        // Ajouter un écouteur de changement de texte au champ de texte
        txtrech.textProperty().addListener((observable, oldValue, newValue) -> {
        rechercherFormation();
    });
        loadFormations("IT");
    }
    
    FormationService fs = new FormationService() ;

    private void loadFormations(String domain_formation) {
        ObservableList<Formation> listef = fs.afficherFormation();
        ObservableList<Formation> filteredList = FXCollections.observableArrayList();
        ObservableList<Formation> otherList = FXCollections.observableArrayList();
        for (int i = 0; i < listef.size(); i++) {
            Formation formation = listef.get(i);
            if (formation.getDomaine_formation().equals(domain_formation)) {
                filteredList.add(formation);
            } else {
                otherList.add(formation);
            }
        }
        filteredList.addAll(otherList);
        titreclm.setCellValueFactory(new PropertyValueFactory<>("titre"));
        dmnclm.setCellValueFactory(new PropertyValueFactory<>("domaine_formation"));
        prclm.setCellValueFactory(new PropertyValueFactory<>("prix"));
        desclm.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(filteredList);
    }
    
    public void rechercherFormation() {
    String keyword = txtrech.getText();
    ObservableList<Formation> filteredList = FXCollections.observableArrayList();
    ObservableList<TableColumn<Formation, ?>> columns = table.getColumns();
    for (int i = 0; i < fs.afficherFormation().size(); i++) {
        Formation formation = fs.afficherFormation().get(i);
        for (int j = 0; j < columns.size(); j++) {
            TableColumn<Formation, ?> column = columns.get(j);
            String cellValue = column.getCellData(formation).toString();
            if (cellValue.contains(keyword)) {
                filteredList.add(formation);
                break;
            }
        }
    }
    table.setItems(filteredList);
}           

    @FXML
    private void AjouterPanier(ActionEvent event) {
        Formation f = table.getSelectionModel().getSelectedItem();
        Ligne_CommandeService ps = new Ligne_CommandeService();
        Ligne_commande lc = new Ligne_commande(1,f.getId_formation(),10,"test",0);
       ps.ajouterCommande(lc);
        //fs.ajouterpanier(f);
        //loadFormations("IT");
    }

    @FXML
    private void voirCours(ActionEvent event) {

    
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