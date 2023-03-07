/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiFormation;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Cours;
import khademni.services.CoursService;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class CoursController implements Initializable {

    @FXML
    private TableColumn<Cours, String> titreclm;
    @FXML
    private TableColumn<Cours, String> desclm;
    @FXML
    private TableView<Cours> table;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadCours();
    }
        CoursService cs = new CoursService();

    private void loadCours() {
        ObservableList<Cours> listef = cs.afficherCours();
        ObservableList<Cours> filteredList = FXCollections.observableArrayList();
        ObservableList<Cours> otherList = FXCollections.observableArrayList();
        
        filteredList.addAll(otherList);
        
        titreclm.setCellValueFactory(new PropertyValueFactory<>("titre"));
        desclm.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        table.setItems(listef);  }

    @FXML
    private void Supprimer(ActionEvent event) {
        Cours c = table.getSelectionModel().getSelectedItem();
        cs.supprimerCours(c);
        loadCours();
    }

    @FXML
    private void Modifier(ActionEvent event) {
        Cours c = table.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierC.fxml"));
            Parent root = loader.load();
            
            ModifierCController controleur = loader.getController();
            
            controleur.setTextFields(c);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnModifier.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            loadCours();
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
