/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Formation;
import khademni.services.FormationService;
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

    
    private Connection myconn =MyConnection.getInstance().getConnexion();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        //fs.ajouterpanier(f);
        //loadFormations("IT");
    }
}