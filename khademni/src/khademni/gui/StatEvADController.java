/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Evenement;
import khademni.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatEvADController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
     private TableView<Evenement> tab_ev_stat;
     @FXML
     private TableColumn titre_stat;
      @FXML
     private TableColumn desc_stat;
       @FXML
     private TableColumn nom_soc_stat;
        @FXML
     private TableColumn lieu_stat;
         @FXML
     private TableColumn date_deb_stat;
          @FXML
     private TableColumn date_fin_stat;
      @FXML
    private TextField idevb;
       @FXML
     private TableView<Evenement> tab_ev_best;
     @FXML
     private TableColumn titre_best;
      @FXML
     private TableColumn desc_best;
       @FXML
     private TableColumn nom_soc_best;
        @FXML
     private TableColumn lieu_best;
         @FXML
     private TableColumn date_deb_best;
          @FXML
     private TableColumn date_fin_best;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        StatEv();
        StatEvBest();
    }    
    
    
     @FXML
    public void StatEv(){
       
         EvenementService es= new EvenementService();
         ObservableList<Evenement> list = es.Stat();
         System.out.println("list ::: "+list);
         titre_stat.setCellValueFactory(new PropertyValueFactory<>("titre"));
         desc_stat.setCellValueFactory(new PropertyValueFactory<>("description"));
         nom_soc_stat.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
         lieu_stat.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         date_deb_stat.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         date_fin_stat.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
      
         tab_ev_stat.setItems(list);
     }
    
     @FXML
    public void StatEvBest(){
       
         EvenementService es= new EvenementService();
         ObservableList<Evenement> list = es.getMostLikedEvents();
         System.out.println("list ::: "+list);
         titre_best.setCellValueFactory(new PropertyValueFactory<>("titre"));
         desc_best.setCellValueFactory(new PropertyValueFactory<>("description"));
         nom_soc_best.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
         lieu_best.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         date_deb_best.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         date_fin_best.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
      
         tab_ev_best.setItems(list);
     }
    
}
