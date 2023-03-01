/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import khademni.entity.Ligne_commande;
import khademni.services.Ligne_CommandeService;

public class PanierGraphController implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;
    
       Ligne_CommandeService us = new Ligne_CommandeService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
          ObservableList<Ligne_commande> list = us.afficherStatistiqiueFormation() ;
          
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

barChart.getData().add(series);
    }
}
