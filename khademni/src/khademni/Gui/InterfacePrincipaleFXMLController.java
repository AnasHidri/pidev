/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class InterfacePrincipaleFXMLController implements Initializable {

    

    /**
     * */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnClient;

    @FXML
    private Button btnEmployeur;

  
  

    @FXML
   void OpenAdmin(ActionEvent event )throws IOException {
         SceneController SC= new SceneController();
         SC.Scene1(event);
   }
    @FXML
    void OpenClient(ActionEvent event) throws IOException {
        SceneController SC= new SceneController();
         SC.Scene3(event);
    }
   @FXML
    void OpenEmployeur(ActionEvent event)throws IOException  {
  SceneController SC= new SceneController();
         SC.Scene2(event);
    }
    }

 


