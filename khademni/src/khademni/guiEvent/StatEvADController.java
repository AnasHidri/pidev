/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
          @FXML
private PieChart pieChart;
          
           @FXML
    private BarChart<String, Number> barChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       EvenementService es= new EvenementService();
       ObservableList<Evenement> list = es.Stat();
         System.out.println("list ::: "+list);
       
       XYChart.Series<String, Number> series = new XYChart.Series<>();
series.setName("Les 3 meilleurs evenements");

int count = 0;
for (Evenement evenement : list) {
    if (count >= 3) { // only show top 3 formations
        break;
    }
    String titre = evenement.getTitre();
    int nombreParticipants = es.countParticipations(evenement.getId_evenement()); // appel à la fonction countParticipations
    series.getData().add(new XYChart.Data<>(titre, nombreParticipants));
    count++;
}
barChart.getData().add(series);

      afficherStatistiques();
    }
      
    
    
     
    
    /* @FXML
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
     }*/
    
  @FXML
public void afficherStatistiques() {
    EvenementService es = new EvenementService();
    ObservableList<Evenement> evenements = es.getMostLikedEvents();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    for (Evenement evenement : evenements) {
        String titre = evenement.getTitre();
        int likes = es.countLikes(evenement.getId_evenement());
        PieChart.Data data = new PieChart.Data("", likes);
        data.setName(titre + " : " + likes + " likes");
        pieChartData.add(data);
    }

    pieChart.setData(pieChartData);
    pieChart.setTitle("Statistiques des événements selon le Vote");
}
}

    
