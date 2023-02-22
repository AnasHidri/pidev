/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import khademni.entity.Offre;
import khademniService.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class EmployeurFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */

  @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;
  
    @FXML
    private TableColumn<?, ?> colAdresse_societe;

    @FXML
    private TableColumn<?, ?> colDate_debut;

    @FXML
    private TableColumn<?, ?> colDate_limite;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine_offre;

    

    @FXML
    private TableColumn<?, ?> colTitre;

    @FXML
    private TextField tfAdressse_societe;

    @FXML
    private TextField tfDate_Limite;

    @FXML
    private TextField tfDate_debut;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfDomaine_offre;

    @FXML
    private TextField tfTitre;

    @FXML
    private TableView<Offre> tvOffre;

 

     @Override
    public void initialize(URL location, ResourceBundle resources) {
        showOffre();
        
    }
    
   @FXML
    public void AddOffre(ActionEvent event) {
        Offre o;
        OffreService os=new OffreService();
        System.out.println("test0");
    // if(event.getSource() == btnAdd){
                   System.out.println("test1");

       String titre= tfTitre.getText();
      String des= tfDescription.getText(); 
     String soc= tfAdressse_societe.getText(); 
     String off=  tfDomaine_offre.getText();
      String debut= tfDate_debut.getText();
      String limite=  tfDate_Limite.getText();
        o =new Offre(2, titre, des, soc, off, debut, limite);
        
     os.ajouterOffre(o);
             System.out.println("test2");
 
   
    }
 public void showOffre(){
  OffreService os =new OffreService(); 

ObservableList<Offre> list1 = os.afficherOffre();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse_societe.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine_offre.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDate_debut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colDate_limite.setCellValueFactory(new PropertyValueFactory <>("date_limite"));
    
    
       tvOffre.setItems(list1);
}  
   
 public void DeleteOffre(ActionEvent event) {
        OffreService os=new OffreService();
     //if(event.getSource() == btnDelete){
           Offre of = tvOffre.getSelectionModel().getSelectedItem();
           System.out.println(of.getId_offre());
       os.supprimerOffre(of);
      os.afficherOffre();
    // }   
     
    }

}

