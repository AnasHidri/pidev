/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mikea
 */
public class Navbar_Navigation {
     private Stage stage;
    private Scene scene ;
    private Parent root;
    
    public void naviger(ActionEvent event,String ch) throws IOException{
             Parent root = FXMLLoader.
                    load(getClass().getResource(ch));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    
   
  
 
    
}
