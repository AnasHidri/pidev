/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Evenement;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
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
           
                          @FXML
      private ComboBox<String> liste_for;
    
    @FXML
       private ComboBox<String> liste_ev;
    @FXML
            private ComboBox<String> liste_off;
    @FXML
            private ComboBox<String> pani;
     @FXML
            private ComboBox<String> stat;
    @FXML
            private Button prof;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         liste_for.getItems().addAll("Liste formation");
            liste_ev.getItems().addAll("Liste evenement");
            liste_off.getItems().addAll("Liste offre");
            pani.getItems().addAll("Liste user", "Liste activation");
            stat.getItems().addAll("Meilleur formations", "Role utilisateur", "Meilleur evenement");
            
            
           liste_for.setOnAction(event -> {
    String selectedPage = (String) liste_for.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste formation")) {
        // navigate to Page 1
    }
});

liste_ev.setOnAction(event -> {
    String selectedPage = (String) liste_ev.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste evenement")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/ListeEvenementAD.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } 
});

liste_off.setOnAction(event -> {
    String selectedPage = (String) liste_off.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste offre")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/OffreAdminFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } 
});

pani.setOnAction(event -> {
    String selectedPage = (String) pani.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste user")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiUser/UsersListFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Liste activation")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiUser/ActivationFormateurFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } 
});
        

stat.setOnAction(event -> {
    String selectedPage = (String) stat.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Meilleur formations")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/gui/PanierGraph.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Role utilisateur")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiUser/DashboardFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Meilleur evenement")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/StatEvAD.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanierFXMLController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    } 
});
       
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

    @FXML
    private void Profile(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiUser/ProfileSettingsFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
}

    
