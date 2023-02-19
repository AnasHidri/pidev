/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Evenement;
import khademni.entity.Participation;
import khademni.services.EvenementService;
import khademni.services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListeEvenementController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
     private TableView<Evenement> tab_ev_liste;
      @FXML
     private TableColumn titre_list;
      @FXML
     private TableColumn desc_list;
      @FXML
     private TableColumn nom_soc_list;
      @FXML
     private TableColumn lieu_list;
      @FXML
     private Button select_ev_list;
      @FXML
     private Button prti_ev_list;
       @FXML
    private TextField idevl;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListEV();
    }    
    
    @FXML
    public void ListEV(){
       
         EvenementService es= new EvenementService();
         ObservableList<Evenement> list = es.getAll();
         System.out.println("list ::: "+list);
         titre_list.setCellValueFactory(new PropertyValueFactory<>("titre"));
         desc_list.setCellValueFactory(new PropertyValueFactory<>("description"));
         nom_soc_list.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
         lieu_list.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         
      
         tab_ev_liste.setItems(list);
         
     }
    
    @FXML
    private void SelectEV(ActionEvent event){
        Evenement selectedEV =  tab_ev_liste.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idevl.setText(String.valueOf(selectedEV.getId_evenement()));

      //  titre_list.setText(selectedEV.getTitre());
       // desc_list.setText(selectedEV.getDescription());
       // nom_soc_list.setText(selectedEV.getNom_societe());
        //lieu_list.setText(selectedEV.getLieu());
    }
    
    @FXML
    private void ParticipeEV(ActionEvent event){
        Evenement selectedEV =  tab_ev_liste.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idevl.setText(String.valueOf(selectedEV.getId_evenement()));
        Participation p= new Participation(selectedEV.getId_evenement(), 2,"active");
        System.out.println("tese1");
        ParticipationService ps= new ParticipationService();
        ps.ajouterParticipation(p);
        System.out.println("tese2");
        
    }
    
    @FXML
    private void AnnulerP(ActionEvent event){
        Evenement selectedEV =  tab_ev_liste.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idevl.setText(String.valueOf(selectedEV.getId_evenement()));
        System.out.println("tese1");
         ParticipationService ps= new ParticipationService();
         ps.supprimerParticipation(selectedEV);
        System.out.println("tese2");
    }
    
}
