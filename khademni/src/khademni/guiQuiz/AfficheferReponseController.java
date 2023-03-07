/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiQuiz;

import khademni.entity.Question;
import khademni.entity.Reponse;
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
import khademni.services.ReponseService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AfficheferReponseController implements Initializable {

    @FXML
    private TableView<Reponse> table;
    @FXML
    private TableColumn<Reponse, String> reponse;
    @FXML
    private TableColumn<Reponse, String> type;

    ReponseService rs = new ReponseService();
    
    public static int idreponse ;
    
   
    private ObservableList<Reponse> UserData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                        try {
            List<Reponse> listb= new ArrayList<Reponse>();
            
            listb = rs.getAllReponse(AfficherQuestionController.idQuestion);
             if(listb != null){
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            reponse.setCellValueFactory
                      (
            new PropertyValueFactory<>("reponse")
                      );
             type.setCellValueFactory
                      (
             new PropertyValueFactory<>("type")
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
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/AjouterReponse.fxml"));
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
        rs.deleteReponse(table.getSelectionModel().getSelectedItem().getId_reponse());
        
        resetTableData();
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
        idreponse =table.getSelectionModel().getSelectedItem().getId_reponse();
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/khademni/guiQuiz/ModfierReponse.fxml"));
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
        List<Reponse> listb = new ArrayList<>();
         listb = rs.getAllReponse(AfficherQuestionController.idQuestion);
        ObservableList<Reponse> data = FXCollections.observableArrayList(listb);
        table.setItems(data);
    }
    
}
