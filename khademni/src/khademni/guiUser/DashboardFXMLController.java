/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiUser;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import khademni.services.UtilisateurService;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private PieChart pieChart;
    
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
        populateChart();
    }
 
    

}
