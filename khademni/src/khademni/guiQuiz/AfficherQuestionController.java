/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiQuiz;

import static khademni.guiQuiz.AfficherQuizController.idQ;
import khademni.entity.Question;
import khademni.entity.Quiz;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.services.QuestionService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AfficherQuestionController implements Initializable {

    @FXML
    private TableView<Question> table;
    @FXML
    private TableColumn<Question, String> question;
    @FXML
    private TableColumn<Question, String> type;
    
    QuestionService qs = new QuestionService();

    public static int idQuestion ;
    
    private ObservableList<Question> UserData = FXCollections.observableArrayList();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                        try {
            List<Question> listb= new ArrayList<Question>();
            
            listb = qs.getAllQuestion(AfficherQuizController.idQ);
            if (listb != null){
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            question.setCellValueFactory
                      (
            new PropertyValueFactory<>("question")
                      );
             type.setCellValueFactory
                      (
                              new PropertyValueFactory<>("type_question")
                      );
            }
        } catch (SQLDataException ex) {
          Logger.getLogger(AfficherQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
         Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/AjouterQuestion.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherQuizController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
           qs.deleteQuestion(table.getSelectionModel().getSelectedItem().getId_question());
           resetTableData();
    }

    @FXML
    private void Modifier(ActionEvent event) {
      idQuestion =  table.getSelectionModel().getSelectedItem().getId_question();   
      
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/ModfierQuestion.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherQuizController.class.getName()).log(Level.SEVERE, null, ex);
               }

        
    }
    
    
        
   public void resetTableData() throws SQLDataException
    {
        List<Question> listb = new ArrayList<>();
         listb = qs.getAllQuestion(AfficherQuizController.idQ);
        ObservableList<Question> data = FXCollections.observableArrayList(listb);
        table.setItems(data);
    }

    @FXML
    private void detail(ActionEvent event) {
        
              idQuestion =  table.getSelectionModel().getSelectedItem().getId_question();   
      
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/AfficherReponse.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
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
