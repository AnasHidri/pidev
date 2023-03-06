/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import khademni.entity.Cours;
import khademni.entity.Formation;
import khademni.services.CoursService;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class AfficherCoursFXMLController implements Initializable {

    @FXML
    private Label cours;
    @FXML
    private TextArea decription;
    @FXML
    private Button affC;
    @FXML
    private Label formaion;
    @FXML
    private TextField id_f;
    
    CoursService cs = new CoursService();
    @FXML
    private TextField path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setTextFields(Formation f){
        
        id_f.setText(String.valueOf(f.getId_formation()));
        Cours c=cs.getCoursByFormationId(f.getId_formation());
        formaion.setText(f.getTitre());  
        String d=c.getDescription();
       decription.setText(d);
       cours.setText(c.getTitre());
       path.setText(c.getFile());
       
    }
    
    @FXML
    public void openCours(){
          try {
              File file = new File(path.getText());
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
