/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiUser;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
import khademni.services.UtilisateurService;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private PieChart pieChart;
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
        populateChart();
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
