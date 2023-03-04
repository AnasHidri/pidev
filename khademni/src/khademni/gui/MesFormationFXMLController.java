/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;
import khademni.services.Ligne_CommandeService;

/**
 * FXML Controller class
 *
 * @author mikea
 */
public class MesFormationFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Ligne_commande> tableviewUser;
    @FXML
    private TableColumn<?, ?> titreform;
    @FXML
    private TableColumn<?, ?> description;
    
        Ligne_CommandeService us = new Ligne_CommandeService();
                
                
    @FXML
      private ComboBox<String> liste_for;
    
    @FXML
       private ComboBox<String> liste_ev;
    @FXML
            private ComboBox<String> liste_off;
    @FXML
            private ComboBox<String> pani;
    @FXML
            private Button prof;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRec();
          liste_for.getItems().addAll("Liste formation","Mes formations");
            liste_ev.getItems().addAll("Liste evenement","Mes participations");
            liste_off.getItems().addAll("Liste offre","Mes candidatures");
            pani.getItems().addAll("Mon panier");
            
            
liste_for.setOnAction(event -> {
    String selectedPage = (String) liste_for.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste formation")) {
        // navigate to Page 1
    } else if (selectedPage.equals("Mes formations")) {
        // navigate to Page 2
    } 
});

liste_ev.setOnAction(event -> {
    String selectedPage = (String) liste_ev.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste evenement")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/ListeEvenement.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Mes participations")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiEvent/MesParticipations.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});

liste_off.setOnAction(event -> {
    String selectedPage = (String) liste_off.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Liste offre")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/ListOffreClientFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Mes candidatures")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiOffre/MesCandidatureClientFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});

pani.setOnAction(event -> {
    String selectedPage = (String) pani.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Mon panier")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/gui/PanierFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});
    }    
    
     @FXML
    public void showRec(){
       
         ObservableList<Ligne_commande> list = us.afficherMesFormation() ;
         System.out.println("list ::: "+list);
         titreform.setCellValueFactory(new PropertyValueFactory<>("titre"));
         description.setCellValueFactory(new PropertyValueFactory<>("prix"));
      
         tableviewUser.setItems(list);
         
      
         
         
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
