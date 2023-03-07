/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiQuiz;

import khademni.entity.Question;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import khademni.services.QuestionService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AjouterQuestionController implements Initializable {

    @FXML
    private AnchorPane Ajouter;
    @FXML
    private TextField Question;
    @FXML
    private ChoiceBox<String> check;

    QuestionService qs = new QuestionService();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        check.getItems().addAll("QCM","QCU");
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
        
        
        Question q = new Question();
        
        q.setQuestion(Question.getText());
        q.setType_question(check.getValue());
        q.setId_quiz(AfficherQuizController.idQ);
        qs.addQuestion(q);
        
              
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/AfficherQuestion.fxml"));
               Stage myWindow = (Stage) Question.getScene().getWindow();
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
