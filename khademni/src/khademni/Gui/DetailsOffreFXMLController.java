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
import khademni.entity.Candidature;
import khademni.entity.Offre;
import khademniService.CandidatureService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class DetailsOffreFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
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

   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      /* public void setTextFields2 (Offre O){
        tfId_offre.setText(String.valueOf(O.getId_offre()));
       tfTitre.setText(O.getTitre());
        tfDescription.setText(O.getDescription());
        tfAdressse_societe.setText(O.getDescription());
        tfDomaine_offre.setText(O.getDomaine_offre());

       
       }
     */

}


