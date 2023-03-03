/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import khademniService.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class StatistiqueFXMLController implements Initializable {

     @FXML
    private PieChart pieChart;
    
        private OffreService os = new OffreService();

    public void PieChart() {
        HashMap<String, Integer> counts = new HashMap<>();
        os.afficherAccepterOffre().stream()

        .filter(offre -> !offre.getDomaine_offre().equals("Domaine_offre"))
        .forEach(offre -> counts.put(offre.getDomaine_offre(), counts.getOrDefault(offre.getDomaine_offre(), 0) + 1));
        pieChart.getData().clear();
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(10);
        pieChart.setLabelsVisible(true);
         pieChart.setClockwise(false);
    pieChart.setAnimated(true);


        counts.forEach((domaine, count) -> pieChart.getData().add(new PieChart.Data(domaine+" : "+count, count)));
        
    }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PieChart();
    }
    
    
    
    
    
   
    
}
