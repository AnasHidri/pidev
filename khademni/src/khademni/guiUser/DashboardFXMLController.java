/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiUser;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import khademni.entity.Evenement;
import khademni.entity.Formation;
import khademni.entity.Ligne_commande;
import khademni.services.EvenementService;
import khademni.services.FormationService;
import khademni.services.Ligne_CommandeService;
import khademni.services.OffreService;
import khademni.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashboardFXMLController implements Initializable {



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
   
    @FXML
    private PieChart pieChartUser;
    @FXML    
    private PieChart pieChartEvent;
    @FXML
    private PieChart chartform;

   
    @FXML
    private BarChart<String, Number> barChartEvent;
    @FXML
    private BarChart<String, Number> barChartPanier;
    @FXML
    private PieChart pieChartOffre;
   
   

             UtilisateurService utilisateurService = new UtilisateurService();
                EvenementService es = new EvenementService();
                       Ligne_CommandeService lc = new Ligne_CommandeService();  
                            OffreService os = new OffreService();
                    FormationService fs = new FormationService();
                           


       
    public void populateChart() {
        HashMap<String, Integer> counts = new HashMap<>();
        utilisateurService.afficherUtilisateurs().stream()
                .filter(user -> !user.getRole().equals("Admin"))
                .forEach(user -> counts.put(user.getRole(), counts.getOrDefault(user.getRole(), 0) + 1));
        pieChartUser.getData().clear();
        pieChartUser.setLegendVisible(false);
        pieChartUser.setStartAngle(90);
        pieChartUser.setLabelsVisible(true);
        pieChartUser.setClockwise(false);
        pieChartUser.setAnimated(true);


        counts.forEach((role, count) -> pieChartUser.getData().add(new PieChart.Data(role+" : "+count, count)));
       
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          statcombo.getItems().addAll("Utilisateurs","Emplois","Formations","Panier","Evenements");
          populateChart();
          afficherStatistiquesEvent1();
          afficherStatistiquesEvent2();
          StatPanier();
                    setLabels();
                    statform();
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
public void afficherStatistiquesEvent1() {
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

@FXML
        public void afficherStatistiquesEvent2(){
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
barChartEvent.getData().add(series);

      afficherStatistiquesEvent1();// A tester demain
        }

       
       
 public void StatPanier(){
 ObservableList<Ligne_commande> list = lc.afficherStatistiqiueFormation() ;
         
XYChart.Series<String, Number> series = new XYChart.Series<>();
series.setName("Les 3 meilleurs formations");

int count = 0;
for (Ligne_commande ligne_commande : list) {
    if (count >= 3) { // only show top 3 formations
        break;
    }
    String nomFormation = ligne_commande.getTitre();
    int nombreCommandes = ligne_commande.getPrix();
    series.getData().add(new XYChart.Data<>(nomFormation, nombreCommandes));
    count++;
}

barChartPanier.getData().add(series);
    }
 
 
 

    public void PieChartOffre() {
        HashMap<String, Integer> counts = new HashMap<>();
        os.afficherAccepterOffre().stream()

        .filter(offre -> !offre.getDomaine_offre().equals("Domaine_offre"))
        .forEach(offre -> counts.put(offre.getDomaine_offre(), counts.getOrDefault(offre.getDomaine_offre(), 0) + 1));
        pieChartOffre.getData().clear();
        pieChartOffre.setLegendVisible(false);
        pieChartOffre.setStartAngle(10);
        pieChartOffre.setLabelsVisible(true);
         pieChartOffre.setClockwise(false);
    pieChartOffre.setAnimated(true);


        counts.forEach((domaine, count) -> pieChartOffre.getData().add(new PieChart.Data(domaine+" : "+count, count)));
       
    }
   

   
   
    public void setLabels(){
       
        nbU.setText(utilisateurService.countUsers()+"");
        nbE.setText(es.countEvenements()+"");
        nbF.setText("2");
        nbO.setText(os.countOffres()+"");
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
           @FXML
    private void stat(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiUser/DashboardFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
   
    public void statform(){
       
        ObservableList<Formation> formations = fs.afficherFormation();
        
        // Count the number of formations per domain
        Map<String, Integer> domainCounts = new HashMap<>();
        for (Formation formation : formations) {
            String domain = formation.getDomaine_formation();
            domainCounts.put(domain, domainCounts.getOrDefault(domain, 0) + 1);
        }
        
        //Afficher le nombre de formation par domaine
        for (String domain : domainCounts.keySet()) {
            int count = domainCounts.get(domain);
            System.out.println("Domaine: " + domain + ", nombre de formations: " + count);
        }
        // Convert the counts to PieChart data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String domain : domainCounts.keySet()) {
            int count = domainCounts.get(domain);
            pieChartData.add(new PieChart.Data(domain + " (" + count + ")", count));
        }
        
        // Set the data for the PieChart
        chartform.setData(pieChartData);
    }
    
}
