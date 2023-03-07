/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.guiQuiz;

import khademni.entity.Quiz;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import khademni.services.QuizService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ModifierQuizController implements Initializable {

    @FXML
    private TextField nom;

    QuizService qs = new QuizService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Quiz q = qs.get_QuizById(AfficherQuizController.idQ);
        System.out.println("helo"+qs.get_QuizById(AfficherQuizController.idQ));
        nom.setText(q.getNom());
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLDataException {
        
        Quiz q = new Quiz();
        q.setId(AfficherQuizController.idQ);
        q.setNom(nom.getText());
        qs.ModifierQuiz(q);
        
             
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/AfficherQuiz.fxml"));
               Stage myWindow = (Stage) nom.getScene().getWindow();
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
