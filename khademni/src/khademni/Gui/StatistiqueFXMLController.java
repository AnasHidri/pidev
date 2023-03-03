/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class StatistiqueFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private CategoryAxis Domaine;

    @FXML
    private NumberAxis NbOffre;

    @FXML
    private StackedBarChart<String,Integer> StackedAreaChart;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        XYChart.Series<String,Integer> series123= new XYChart.Series<>();
    
StackedAreaChart.getData().add(series123);
        
                }    
    
}
