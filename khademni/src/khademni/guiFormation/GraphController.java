/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiFormation;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import khademni.entity.Formation;
import khademni.services.FormationService;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class GraphController implements Initializable {

    @FXML
    private PieChart chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FormationService fs = new FormationService();
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
        ObservableList<Data> pieChartData = FXCollections.observableArrayList();
        for (String domain : domainCounts.keySet()) {
            int count = domainCounts.get(domain);
            pieChartData.add(new Data(domain + " (" + count + ")", count));
        }
        
        // Set the data for the PieChart
        chart.setData(pieChartData);

    }    
    
}
