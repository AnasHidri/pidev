/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;
import khademni.services.Ligne_CommandeService;
import khademni.services.PanierService;

/**
 * FXML Controller class
 *
 * @author mikea
 */
public class PanierFXMLController implements Initializable {

     @FXML
    private TableView<Ligne_commande> tableviewUser;
    @FXML
    private TableColumn<?, ?> idform;
    @FXML
    private TableColumn<?, ?> prixform;
    @FXML
    private TextField prixtotal;

    
    Ligne_CommandeService us = new Ligne_CommandeService();
     PanierService p= new PanierService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
       showRec();
        
    }    
    

    
    @FXML
    public void showRec(){
       
         ObservableList<Ligne_commande> list = us.afficherLigneCommande() ;
         System.out.println("list ::: "+list);
         idform.setCellValueFactory(new PropertyValueFactory<>("titre"));
         prixform.setCellValueFactory(new PropertyValueFactory<>("prix"));
      
         tableviewUser.setItems(list);
         
        int prixtot=0;
        Panier panier = new Panier(10,100);
        p.modifierPanier(panier);
       prixtot= p.affichesomme(panier);
         prixtotal.setText("Prix totale : "+prixtot+"");
         
         
     }
    
    @FXML
    public void supprimer(ActionEvent event) throws IOException{
          Ligne_commande selectedLN =  tableviewUser.getSelectionModel().getSelectedItem();
       System.out.println(selectedLN.getId_ligne_commande());
       us.supprimerCommande(selectedLN);
            showRec();
   
    }
    
    public void payer(ActionEvent event) {
        Panier panier = new Panier(10,100);
        p.payer(panier);
          Ligne_commande commande= new Ligne_commande(2,1,500,"SQL",0);
        us.ViderCommande(panier);
        showRec();
    }
    
}
