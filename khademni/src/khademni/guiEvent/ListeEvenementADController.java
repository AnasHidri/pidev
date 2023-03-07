/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiEvent;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.Logger;
import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Evenement;
import khademni.api.PdfEv;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
import khademni.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */



public class ListeEvenementADController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
     private TableView<Evenement> tab_liste_ev_ad;
      @FXML
     private TableColumn titre_ev_ad;
      @FXML
     private TableColumn desc_ev_ad;
      @FXML
     private TableColumn nomsoc_ev_ad;
      @FXML
     private TableColumn lieu_ev_ad;
      @FXML
     private TableColumn date_deb_ev_ad;
      @FXML
     private TableColumn date_fin_ev_ad;
      @FXML
      private Button btn_stat;
      @FXML
      private Button cons_part;
                   @FXML
      private ComboBox<String> liste_for;
    
    @FXML
       private ComboBox<String> liste_ev;
    @FXML
            private ComboBox<String> liste_off;
    @FXML
            private ComboBox<String> pani;
     @FXML
            private Button stat;
    @FXML
            private Button prof;
  
      
      @Override
    public void initialize(URL url, ResourceBundle rb) {
         liste_for.getItems().addAll("Liste formation");
            liste_ev.getItems().addAll("Liste evenement");
            liste_off.getItems().addAll("Liste offre");
            pani.getItems().addAll("Liste user", "Liste activation");
           
            
            
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
        


        // TODO
        ListEV();
    }    
    
    @FXML
    public void ListEV(){
       
         EvenementService es= new EvenementService();
         ObservableList<Evenement> list = es.getAll();
         System.out.println("list ::: "+list);
         titre_ev_ad.setCellValueFactory(new PropertyValueFactory<>("titre"));
         desc_ev_ad.setCellValueFactory(new PropertyValueFactory<>("description"));
         nomsoc_ev_ad.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
         lieu_ev_ad.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         date_deb_ev_ad.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         date_fin_ev_ad.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
      
         tab_liste_ev_ad.setItems(list);
         
     }
   
   /* @FXML
    public void GenererPdfEv(ActionEvent event){
         EvenementService es= new EvenementService();
      ObservableList<Evenement> list = es.getAll();
      
       PdfEv pd=new PdfEv();
        try{
                     pd.generatePdf("evenements.pdf", list);

            System.out.println("impression done");
        } catch  (Exception ex) {
        ex.printStackTrace();
            }
    }*/
    
    @FXML
public void ConsulterParticipants(ActionEvent event) throws IOException {
Evenement selectedEV = tab_liste_ev_ad.getSelectionModel().getSelectedItem();
int eventId = selectedEV.getId_evenement(); // Récupérer l'identifiant de l'événement sélectionné
System.out.println("id_e::" + eventId);
try {
// Charger la nouvelle vue
FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiEvent/LesParticipationsAD.fxml"));
Parent root = loader.load();


    // Obtenir le contrôleur de la nouvelle vue
    LesParticipationsADController controleur = loader.getController();

    // Passer les données de l'utilisateur actuel et l'identifiant de l'événement sélectionné au nouveau contrôleur
    controleur.setTextFields(eventId);
  

    Scene scene = new Scene(root);
    Stage stage = (Stage) cons_part.getScene().getWindow();
    stage.setScene(scene);
    stage.show();

} catch (IOException e) {
    System.out.println(e.getCause().getMessage());
}
    }

     @FXML
    private void StatEvPart(ActionEvent event) throws IOException{
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatEvAD.fxml"));
             Parent root = loader.load();

              Scene scene = new Scene(root);
               Stage stage = (Stage) btn_stat.getScene().getWindow();
                stage.setScene(scene);
                 stage.show();

                  }catch(IOException ex){
                      System.out.println(ex.getCause().getMessage());
                      }
    }
    
    
        @FXML
    private void Profile(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/gui/HistoriqueFXML.fxml"));
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
}