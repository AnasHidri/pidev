/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.guiQuiz;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import khademni.entity.Quiz;
import khademni.services.QuizService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ShowQuizController implements Initializable {

    @FXML
    private ListView<Quiz> listView;
    
     ObservableList<Quiz> data;
     
     public static int idQf ;
     
     QuizService qs = new QuizService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try {
            data = (ObservableList<Quiz>) qs.getAllQuiz();   
            listView.setItems(data);
            listView.setCellFactory((ListView<Quiz> param) -> new ListViewQuiz());
            
            
            // TODO
        } catch (SQLDataException ex) {
                    Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Passer(ActionEvent event) {
        
               ObservableList<Quiz> e = listView.getSelectionModel().getSelectedItems();     
               for (Quiz m : e) {
               idQf = m.getId();  
                }
               
                             
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/ShowQuestion.fxml"));
               Stage myWindow = (Stage) listView.getScene().getWindow();
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
