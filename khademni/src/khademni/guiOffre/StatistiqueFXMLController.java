/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiOffre;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import khademni.services.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class StatistiqueFXMLController implements Initializable {

     @FXML
    private PieChart pieChart;
    
        private OffreService os = new OffreService();

        @FXML
    public void PieChart() {
        HashMap<String, Integer> counts = new HashMap<>();
        os.afficherAccepterOffre().stream()
                .forEach(offre -> counts.put(offre.getDomaine_offre(), counts.getOrDefault(offre.getId_offre(), 0) + 1));
        pieChart.getData().clear();
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(90);
        pieChart.setLabelsVisible(true);
        pieChart.setClockwise(false);
        pieChart.setAnimated(true);

        counts.forEach((domaine_offre, count) -> pieChart.getData().add(new PieChart.Data(domaine_offre+" : "+count, count)));
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PieChart();
    }
    
    
    
    
    
   
    
}
