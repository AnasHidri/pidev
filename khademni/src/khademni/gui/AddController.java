/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import khademni.entity.Formation;
import khademni.services.FormationService;
import khademni.utils.MyConnection;
import java.sql.SQLException;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;


/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class AddController {

    @FXML
    private Button btnConfirmer;

    @FXML
    private TextArea txtdescription;

    @FXML
    private TextField txtdomaine;

    @FXML
    private TextField txtprix;

    @FXML
    private TextField txttitre;
    
    @FXML
    private AnchorPane AnchorPane;
    
    Connection myconn =MyConnection.getInstance().getConnexion();
    FormationService fs = new FormationService();
    

    @FXML
    public void initialize() {
        // Définit les contraintes de validation pour chaque champ de saisie
        txttitre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txttitre.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtdomaine.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                txtdomaine.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        txtprix.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtprix.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void confirmer() {
        // Vérifie que tous les champs ont été remplis
        if (txttitre.getText().isEmpty() || txtdomaine.getText().isEmpty() || txtprix.getText().isEmpty() || txtdescription.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis !");
            alert.showAndWait();
            return;
        }

        String titre = txttitre.getText();
        String domaine = txtdomaine.getText();
        double prix = Double.parseDouble(txtprix.getText());
        String description = txtdescription.getText();
        Formation f = new Formation(1,titre, domaine, prix, description);
        fs.ajouterFormation(f);
        
        // Affiche un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Formation ajoutée avec succès !");
        alert.showAndWait();

        // Réinitialise les champs de saisie
        txttitre.setText("");
        txtdomaine.setText("");
        txtprix.setText("");
        txtdescription.setText("");
    }   
}