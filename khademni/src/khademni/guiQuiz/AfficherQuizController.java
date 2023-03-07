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
import khademni.services.QuizService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AfficherQuizController implements Initializable {

    @FXML
    private TableView<Quiz> table;
    @FXML
    private TableColumn<Quiz, String> nom;
    
    QuizService cs = new QuizService();

    public static int idQ ;
    private ObservableList<Quiz> UserData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                try {
            List<Quiz> listb= new ArrayList<Quiz>();
            
            listb = cs.getAllQuiz();
     
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            nom.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nom")
                      );
        } catch (SQLDataException ex) {
            Logger.getLogger(AfficherQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
                Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/AjouterQuiz.fxml"));
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
        
      int id =  table.getSelectionModel().getSelectedItem().getId();   
       cs.deleteQuiz(id);
       resetTableData();

    }

    @FXML
    private void Modifier(ActionEvent event) {
              idQ =  table.getSelectionModel().getSelectedItem().getId();   
              Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/ModiferQuiz.fxml"));
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
        List<Quiz> lisre = new ArrayList<>();
        lisre = cs.getAllQuiz();
        ObservableList<Quiz> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }



    @FXML
    private void details(ActionEvent event) {
        idQ =  table.getSelectionModel().getSelectedItem().getId();
        Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/AfficherQuestion.fxml"));
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
