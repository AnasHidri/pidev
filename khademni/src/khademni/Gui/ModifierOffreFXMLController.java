/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import khademni.entity.Offre;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class ModifierOffreFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private Button btnEmployeur1;
   @FXML
    private TextField tfId_offre;
    @FXML
    private TextField tfAdressse_societe;

    @FXML
    private TextField tfDate_Limite;

    @FXML
    private TextField tfDate_debut;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfDomaine_offre;

    @FXML
    private TextField tfTitre;

   
    @FXML
    void SetTFOffre( Offre o) {

        tfId_offre.setText(String.valueOf(o.getId_offre()));
       tfTitre.setText(o.getTitre());
        tfDescription.setText(o.getDescription());
        tfAdressse_societe.setText(o.getAdresse_societe());
        tfDomaine_offre.setText(o.getDomaine_offre());
       // tfDate_debut.setValue(LocalDate.parse(String.valueOf(o.getDate_debut())));
       // tfDate_Limite.setValue(LocalDate.parse(String.valueOf(o.getDate_limite())));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
