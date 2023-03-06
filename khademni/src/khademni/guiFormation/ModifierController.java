/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiFormation;

import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import khademni.entity.Formation;
import khademni.services.FormationService;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField txttitre;
    @FXML
    private TextField txtdomaine;
    @FXML
    private TextField txtprix;
    @FXML
    private TextArea txtdescription;
    @FXML
    private Button btnConfirmer;
    @FXML
    private TextField id;
    @FXML
      private ComboBox<String> liste_for;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           liste_for.getItems().addAll("Liste formation","Mes formations");
          

liste_for.setOnAction(event -> {
    String selectedPage = (String) liste_for.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste formation")) {
        // navigate to Page 1
    } else if (selectedPage.equals("Mes formations")) {
        // navigate to Page 2
    } 
});
    }    

    @FXML
    private void confirmer(ActionEvent event) {
         FormationService fs= new FormationService();
         
         Integer id2=Integer.valueOf(id.getText());
         System.out.println("idd :::: "+id);
         String titre2=txttitre.getText();
         String domaine2=txtdomaine.getText();
         Double prix2=Double.valueOf(txtprix.getText());
         String description2=txtdescription.getText();
         
         Formation f =new Formation(id2, description2, titre2, domaine2, prix2);
           fs.modifierFormation(f);
           System.out.println("f :: "+f);
          
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Formation Modifié avec succés !");
                alert.showAndWait();  
        
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Formation.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnConfirmer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }    
    }
    
    public void setTextFields(Formation f){
        id.setText(String.valueOf(f.getId_formation()));
        txttitre.setText(f.getTitre());
        txtdomaine.setText(f.getDomaine_formation());
        txtprix.setText(String.valueOf(f.getPrix()));
        txtdescription.setText(f.getDescription());    
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
    
}
