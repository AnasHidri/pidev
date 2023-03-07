/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.guiQuiz;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
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
import khademni.entity.Question;
import khademni.entity.Reponse;
import khademni.services.ReponseService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ShowReponseController implements Initializable {

    @FXML
    private ListView<Reponse> listView;
    
    ObservableList<Reponse> data;
    
     public static int idreponsef ;
     
     public static int count =0 ;
     ReponseService rs = new ReponseService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                                try {
            data = (ObservableList<Reponse>) rs.getAllReponse(ShowQuestionController.idQuestionf);   
            listView.setItems(data);
            listView.setCellFactory((ListView<Reponse> param) -> new ListViewReponse());
            
            
            // TODO
        } catch (SQLDataException ex) {
                    Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Passer(ActionEvent event) {
        
                ObservableList<Reponse> e = listView.getSelectionModel().getSelectedItems();     
               for (Reponse m : e) {
               if (m.getType().equals("true")){
               
                 count++ ;
               }  
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
