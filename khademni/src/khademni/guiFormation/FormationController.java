/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiFormation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import khademni.services.FormationService;
import khademni.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class FormationController implements Initializable {


    @FXML
    private Button btnModifier;
    
    @FXML
    private Button btnSupprimer;
    
    @FXML
    private TableView<Formation> table;

    @FXML
    private TableColumn<Formation, String> dmnclm;

    @FXML
    private TableColumn<Formation, Double> prclm;

    @FXML
    private TableColumn<Formation, String> titreclm;
    
    @FXML
    private TableColumn<Formation, String> desclm;

    @FXML
    private TextField txtrech;
    
    private ObservableList<Formation> formationList;

    
    private Connection myconn =MyConnection.getInstance().getConnexion();
    @FXML
    private Button btnCours;
     @FXML
      private ComboBox<String> liste_for;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         liste_for.getItems().addAll("Liste formation","Mes formations");
          

liste_for.setOnAction(event -> {
    String selectedPage = (String) liste_for.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste formation")) {
        // navigate to Page 1
    } else if (selectedPage.equals("Mes formations")) {
        // navigate to Page 2
    } 
});
        
       // Ajouter un écouteur de changement de texte au champ de texte
        txtrech.textProperty().addListener((observable, oldValue, newValue) -> {
        rechercherFormation();
    });
        loadFormations();
    }
    
    FormationService fs = new FormationService() ;

    private void loadFormations() {

        ObservableList<Formation> listef = fs.getFormationsByFormateur(1);
        titreclm.setCellValueFactory(new PropertyValueFactory<>("titre"));
        dmnclm.setCellValueFactory(new PropertyValueFactory<>("domaine_formation"));
        prclm.setCellValueFactory(new PropertyValueFactory<>("prix"));
        desclm.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.setItems(listef);
    }
    
    
    public void rechercherFormation() {
        String keyword = txtrech.getText();
        ObservableList<Formation> filteredList = FXCollections.observableArrayList();
        ObservableList<TableColumn<Formation, ?>> columns = table.getColumns();
        for (int i = 0; i < fs.getFormationsByFormateur(1).size(); i++) {
            Formation formation = fs.getFormationsByFormateur(1).get(i);
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
    void Supprimer(ActionEvent event) { 
        Formation f = table.getSelectionModel().getSelectedItem();
        fs.supprimerFormation(f);
        loadFormations();
    }
    
    @FXML
    public void modifierFormation() {
        Formation f = table.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier.fxml"));
            Parent root = loader.load();
            
            ModifierController controleur = loader.getController();
            
            controleur.setTextFields(f);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnModifier.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            loadFormations();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
}

    @FXML
    private void ajouterCours(ActionEvent event) {
        Formation f = table.getSelectionModel().getSelectedItem();
        try {
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addcours.fxml"));
            Parent root = loader.load();
            
             AddcoursController controleur = loader.getController();
            
            controleur.setTextFields(f);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnCours.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
    
    
    @FXML
    public void afficherCours(){
        Formation f = table.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCoursFXML.fxml"));
            Parent root = loader.load();
            
            AfficherCoursFXMLController controleur = loader.getController();
            
            controleur.setTextFields(f);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnModifier.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            loadFormations();
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
       @FXML
        private void LesFormations(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiFormation/Formation.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
        
              @FXML
        private void MesFormations(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/gui/MesFormationFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
}