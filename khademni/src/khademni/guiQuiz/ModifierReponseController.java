/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiQuiz;

import khademni.entity.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import khademni.services.ReponseService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class ModifierReponseController implements Initializable {

    @FXML
    private AnchorPane Ajouter;
    @FXML
    private TextField reponse;
    @FXML
    private ChoiceBox<String> choix;
    
    ReponseService rs = new ReponseService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      choix.getItems().addAll("true","false");
        Reponse r = rs.get_ReponseById(AfficheferReponseController.idreponse);
        reponse.setText(r.getReponse());
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLDataException {
        
        Reponse r = new Reponse();
        r.setId_reponse(AfficheferReponseController.idreponse);
        r.setType(choix.getValue());
        r.setReponse(reponse.getText());
        
        rs.ModifierReponse(r);
        
          Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/AfficherReponse.fxml"));
               Stage myWindow = (Stage) reponse.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherQuizController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
    }
    
}
