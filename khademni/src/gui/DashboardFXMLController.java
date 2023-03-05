/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashboardFXMLController implements Initializable {
@FXML
    private PieChart pieChart;
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
    
    public void setLabels(){
        
        nbU.setText(utilisateurService.countUsers()+"");
        nbE.setText(utilisateurService.countUsers()+"");
        nbF.setText(utilisateurService.countUsers()+"");
        nbO.setText(utilisateurService.countUsers()+"");
    }
  
    
}
