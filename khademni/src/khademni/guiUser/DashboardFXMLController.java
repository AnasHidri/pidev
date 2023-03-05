/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiUser;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import khademni.entity.Evenement;
import khademni.services.EvenementService;
import khademni.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashboardFXMLController implements Initializable {
@FXML
    private PieChart pieChart;
@FXML    private PieChart pieChartEvent;


   @FXML
    private ComboBox statcombo;
     @FXML
    private AnchorPane statU;
    
    @FXML
    private AnchorPane Stattt;
    @FXML
    private AnchorPane statForm;
    @FXML
    private AnchorPane statEv;
    @FXML
    private AnchorPane statOf;
    @FXML
    private AnchorPane statPanier;
    @FXML
    private Label nbU;
    @FXML
    private Label nbO;
    @FXML
    private Label nbF;
    @FXML
    private Label nbE;

            private UtilisateurService utilisateurService = new UtilisateurService();

        
    public void populateChart() {
        HashMap<String, Integer> counts = new HashMap<>();
        utilisateurService.afficherUtilisateurs().stream()
                .filter(user -> !user.getRole().equals("Admin"))
                .forEach(user -> counts.put(user.getRole(), counts.getOrDefault(user.getRole(), 0) + 1));
        pieChart.getData().clear();
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(90);
        pieChart.setLabelsVisible(true);
        pieChart.setClockwise(false);
        pieChart.setAnimated(true);


        counts.forEach((role, count) -> pieChart.getData().add(new PieChart.Data(role+" : "+count, count)));
        
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          statcombo.getItems().addAll("Utilisateurs","Emplois","Formations","Panier","Evenements");
          populateChart();
          afficherStatistiques();
                    setLabels();
                    statcombo.setOnAction(this::handleRoleSelection);
                  
    }
    
    private void handleRoleSelection(Event event) {
        
    String selectedStat = statcombo.getValue().toString();

    switch (selectedStat) {
        case "Utilisateurs":
            statU.setVisible(true); 
            statForm.setVisible(false); 
            statEv.setVisible(false); 
            statOf.setVisible(false); 
            statPanier.setVisible(false); 
            break;
        case "Emplois":
            statU.setVisible(false); 
            statForm.setVisible(false); 
            statEv.setVisible(false); 
            statOf.setVisible(true); 
            statPanier.setVisible(false); 
            break;
        case "Formations":
            statU.setVisible(false); 
            statForm.setVisible(true); 
            statEv.setVisible(false); 
            statOf.setVisible(false); 
            statPanier.setVisible(false); 
            break;
            case "Evenements":
            statU.setVisible(false); 
            statForm.setVisible(false); 
            statEv.setVisible(true); 
            statOf.setVisible(false); 
            statPanier.setVisible(false); 
            break;
            case "Panier":
            statU.setVisible(false); 
            statForm.setVisible(false); 
            statEv.setVisible(false); 
            statOf.setVisible(false); 
            statPanier.setVisible(true); 
            break;
        default:
            break;
    }
}
    
    @FXML
public void afficherStatistiques() {
    ObservableList<Evenement> evenements = es.getMostLikedEvents();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    for (Evenement evenement : evenements) {
        String titre = evenement.getTitre();
        int likes = es.countLikes(evenement.getId_evenement());
        PieChart.Data data = new PieChart.Data("", likes);
        data.setName(titre + " : " + likes + " likes");
        pieChartData.add(data);
    }

    pieChartEvent.setData(pieChartData);
    pieChartEvent.setTitle("Statistiques des événements selon le Vote");
}
/*
@FXML
        public void affichStat2(){
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
*/

    
    EvenementService es = new EvenementService();
    
    public void setLabels(){
        
        nbU.setText(utilisateurService.countUsers()+"");
        nbE.setText(es.countEvenements()+"");
        nbF.setText(utilisateurService.countUsers()+"");
        nbO.setText(utilisateurService.countUsers()+"");
    }
  
    
}