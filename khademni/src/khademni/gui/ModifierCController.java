/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import khademni.entity.Cours;
import khademni.services.CoursService;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class ModifierCController implements Initializable {

    @FXML
    private TextField txttitre;
    @FXML
    private TextField txtdescription;
    @FXML
    private Button btnConfirmer;
    @FXML
    private TextField id;
    @FXML
    private TextField txtFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmer(ActionEvent event) {
        CoursService cs = new CoursService();
        
         Integer id2=Integer.valueOf(id.getText());
         System.out.println("idd :::: "+id);
         String titre2=txttitre.getText();
         String description2=txtdescription.getText();
         String file2=txtFile.getText();
         
         Cours c =new Cours(titre2, description2, file2, id2);
           cs.modifierCours(c);
           System.out.println("c :: "+c);
          
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Cours Modifié avec succés !");
                alert.showAndWait();  
        
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cours.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnConfirmer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        } 
    }
    
    public void setTextFields(Cours c){
        id.setText(String.valueOf(c.getId_cours()));
        txtFile.setText(c.getFile());
        txttitre.setText(c.getTitre());
        txtdescription.setText(c.getDescription());    
    }
    
}
