/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class OffreClientFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private Button Btn;

    @FXML
    private TableColumn<?, ?> colAdresse;

    @FXML
    private TableView<?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine;

    @FXML
    void AddMesOffres(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    
    
}
