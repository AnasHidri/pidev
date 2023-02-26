/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.log.Logger;
import com.sun.javafx.logging.PlatformLogger.Level;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Evenement;
import khademni.entity.PdfEv;
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
      private Button btn_pdf_ev;
    
  
      
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
         titre_ev_ad.setCellValueFactory(new PropertyValueFactory<>("titre"));
         desc_ev_ad.setCellValueFactory(new PropertyValueFactory<>("description"));
         nomsoc_ev_ad.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
         lieu_ev_ad.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         date_deb_ev_ad.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
         date_fin_ev_ad.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
      
         tab_liste_ev_ad.setItems(list);
         
     }
   
    @FXML
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
    }
    
}
