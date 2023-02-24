/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
public class MesParticipationsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
     private TableView<Evenement> tab_mes_parti;
     @FXML
     private TableColumn titre_ma_liste;
     @FXML
     private TableColumn desc_ma_liste;
     @FXML
     private TableColumn nom_soc_ma_liste;
     @FXML
     private TableColumn lieu_ma_liste;
     @FXML
     private Button btn_select_ma_liste;
      @FXML
     private Button btn_annuler_parti_ma_liste;
      @FXML
    private TextField idev2;
      @FXML
      private Button btn_like;
      @FXML
      private Button btn_dislike;
      @FXML
      private TextField recherche_text_par;
      @FXML
      private Button btn_recherche;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        MesParticipations();
    }    
    
     @FXML
      public void MesParticipations(){
     EvenementService es= new EvenementService();
         ObservableList<Evenement> liste = es.MaListe();
         System.out.println("list ::: "+liste);
         titre_ma_liste.setCellValueFactory(new PropertyValueFactory<>("titre"));
         desc_ma_liste.setCellValueFactory(new PropertyValueFactory<>("description"));
         nom_soc_ma_liste.setCellValueFactory(new PropertyValueFactory<>("nom_societe"));
         lieu_ma_liste.setCellValueFactory(new PropertyValueFactory<>("lieu"));
         
      
         tab_mes_parti.setItems(liste);
      }
      
      @FXML
    private void SelectEV(ActionEvent event){
        Evenement selectedEV =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV);
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));

      
    }
  /*  @FXML
    private void SelectP(ActionEvent event){
        Participation selectedPa =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_p::"+selectedPa);
        idev2.setText(String.valueOf(selectedPa.getId_evenement()));

      
    }*/
    
     @FXML
    private void AnnulerP(ActionEvent event){
        Evenement selectedEV =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));
        System.out.println("tese1");
         ParticipationService ps= new ParticipationService();
         ps.supprimerParticipation(selectedEV);
        System.out.println("tese2");
        MesParticipations();
    }
    
    @FXML
            private void LikeE(ActionEvent event){
                 Evenement selectedEV =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));
        Participation p= new Participation(selectedEV.getId_evenement(), 2,"active");
        System.out.println("tese1");
        ParticipationService ps= new ParticipationService();
        ps.likeEvent(p);
        System.out.println("tese2");
            }
            
            @FXML
            private void DislikeE(ActionEvent event){
                 Evenement selectedEV =  tab_mes_parti.getSelectionModel().getSelectedItem();
        System.out.println("id_e::"+selectedEV.getId_evenement());
        idev2.setText(String.valueOf(selectedEV.getId_evenement()));
         Participation p= new Participation(selectedEV.getId_evenement(), 2,"active");
        System.out.println("tese1");
        ParticipationService ps= new ParticipationService();
        ps.DislikeEvent(p);
        System.out.println("tese2");
            }
            
    /*@FXML
private void showLikesAndDislikes(ActionEvent event) {
    Evenement selectedEvent =  tab_mes_parti.getSelectionModel().getSelectedItem();
    Participation p = new Participation(selectedEvent.getId_evenement(), 0, "");
    int[] counts = participationService.getLikesAndDislikesCount(p);
    int likes = counts[0];
    int dislikes = counts[1];
    String message = "Likes : " + likes + ", Dislikes : " + dislikes;
    likesAndDislikesLabel.setText(message);
}
Dans cet exemple, nous appelons la méthode getLikesAndDislikesCount à partir du service participationService, en passant une instance de Participation contenant l'ID de l'événement sélectionné. Nous stockons le nombre de likes et de dislikes dans les variables likes et dislikes, puis nous construisons une chaîne de caractères message pour afficher les résultats. Enfin, nous mettons à jour l'étiquette likesAndDislikesLabel avec le message.
*/
            
            @FXML
        private void rechercheEvenement(ActionEvent event) {
        // Récupérer le titre de l'événement à rechercher depuis le champ de texte
         String titreEvenement = recherche_text_par.getText();

       
      // Récupérer la liste des événements auxquels l'utilisateur a participé depuis la TableView
        ObservableList<Evenement> evenements = tab_mes_parti.getItems();

       // Créer une nouvelle liste pour stocker les résultats de la recherche
        ObservableList<Evenement> evenementsRecherche = FXCollections.observableArrayList();

         // Parcourir la liste des événements et ajouter les événements correspondant au titre de recherche
         for (Evenement evenement : evenements) {
          if (evenement.getTitre().toLowerCase().contains(titreEvenement.toLowerCase())) {
            evenementsRecherche.add(evenement);
        }
      }

        if (!evenementsRecherche.isEmpty()) {
       // Afficher les résultats de la recherche dans la TableView
        tab_mes_parti.setItems(evenementsRecherche);
      } else {
       // Afficher un message d'erreur si aucune participation n'a été trouvée
       System.out.println("Aucune participation trouvée pour l'événement " + titreEvenement);
              }


     }
}
