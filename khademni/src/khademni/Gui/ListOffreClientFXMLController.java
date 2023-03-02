/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import khademni.entity.Candidature;
import khademni.entity.Offre;
import khademniService.CandidatureService;
import khademniService.OffreService;

/**
 * FXML Controller class
 *
 * @author CYBERLAND
 */
public class ListOffreClientFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnPost;
    @FXML
    private Button btnretourne;
      @FXML
    private Button btnmescandid;
    @FXML
    private TableColumn<?, ?> colAdresse;

    @FXML
    private TableColumn<?, ?> colDateDebut;

    @FXML
    private TableColumn<?, ?> colDateLimite;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDomaine;

    @FXML
    private TableColumn<?, ?> colTitre;
        @FXML
    private TextField tfRecherche;

    @FXML
    void AddOffre(ActionEvent event) {

    }
    
    @FXML
    private TableView<Offre> tvOffre23;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  showOffre();
    }    
      @FXML
    void Recherche(ActionEvent event) {
 

  OffreService os =new OffreService(); 
        FilteredList<Offre> filter = new FilteredList<>(os.afficherOffre(), e -> true);

        tfRecherche.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateOffreData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateOffreData.getTitre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateOffreData.getDomaine_offre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateOffreData.getAdresse_societe().toLowerCase().contains(searchKey)) {
                    return true;
              
                } else 
                    return false;
                
            });
        });

        SortedList<Offre> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(tvOffre23.comparatorProperty());
        tvOffre23.setItems(sortList);
    
    }

    
     public void showOffre(){
           OffreService os =new OffreService(); 

ObservableList<Offre> list23 = os.afficherAccepterOffre();


colTitre.setCellValueFactory(new PropertyValueFactory <>("titre"));
colDescription.setCellValueFactory(new PropertyValueFactory <>("description"));
colAdresse.setCellValueFactory(new PropertyValueFactory <>("adresse_societe"));
colDomaine.setCellValueFactory(new PropertyValueFactory <>("domaine_offre"));
colDateDebut.setCellValueFactory(new PropertyValueFactory <>("date_debut"));
colDateLimite.setCellValueFactory(new PropertyValueFactory <>("date_limite"));
    
       tvOffre23.setItems(list23);   
}
     
   @FXML
    void AddCandidature(ActionEvent event) throws IOException {
         Offre selectedOffre = tvOffre23.getSelectionModel().getSelectedItem();
        System.out.println("id_o::"+selectedOffre.getId_offre());
      //  selectedOffre.getId_offre.setText(String.valueOf(selectedOffre.getId_offre()));
        Candidature C= new Candidature(selectedOffre.getId_offre(), 1,"en attente");
        CandidatureService CV= new CandidatureService();
        CV.ajouterCandidature(C);
      
        
    }
  
    
      @FXML
    void retourneinterPrin(ActionEvent event) throws IOException {
    SceneController SC = new SceneController();
    SC.Scene4(event);
      

    }
    
     @FXML
    void MesCandidature(ActionEvent event) throws IOException {
 SceneController SC = new SceneController();
    SC.Scene6(event);
    }
}
