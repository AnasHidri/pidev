/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import khademni.entity.Utilisateur;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
import khademni.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UsersListFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Utilisateur> tableviewUser;
    
    @FXML
    private TableColumn<?, ?> nomUser;
    @FXML
    private TableColumn<?, ?> prenomUser;
    @FXML
    private TableColumn<?, ?> roleUser;
    @FXML
    private TableColumn<?, ?> etatUser;
    @FXML
    private TableColumn<?, ?> emailUser;
    
     @FXML
    private Button btn_modifier;
     
     @FXML
    private TextField Recherche_User;
     
     @FXML
         private AnchorPane AnchorPane;
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
   
    
    UtilisateurService us = new UtilisateurService();
    @FXML
    private Button btn_filter;
    @FXML
    private ComboBox rolechoice;
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
        

                
     makeFadeInTransition() ;
                showUsers();
                searchRec();
    rolechoice.getItems().addAll("Client","Formateur","Employeur");

    }
   
    @FXML
    private void filterByRole(ActionEvent event) {
    String selectedRole = (String) rolechoice.getSelectionModel().getSelectedItem();

    FilteredList<Utilisateur> filteredData = tableviewUser.getItems().filtered(user -> true);

    Predicate<Utilisateur> rolePredicate = user -> user.getRole().equals(selectedRole);

    Predicate<Utilisateur> etatPredicate;
    if (selectedRole.equals("Formateur")) {
        etatPredicate = user -> user.getEtat().equals("actif");
    } else {
        etatPredicate = user -> true;
    }

    filteredData.setPredicate(rolePredicate.and(etatPredicate));
   
    tableviewUser.setItems(filteredData);
}


   
    @FXML
public void showUsers() {
    ObservableList<Utilisateur> list = us.afficherUtilisateurs();

    ObservableList<Utilisateur> filteredList = FXCollections.observableArrayList();

    for (Utilisateur user : list) {
        if (user.getRole().equals("Formateur") && user.getEtat().equals("actif")) {
            filteredList.add(user);
        } else if (!user.getRole().equals("Formateur")) {
            filteredList.add(user);
        }
    }

    nomUser.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomUser.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    roleUser.setCellValueFactory(new PropertyValueFactory<>("login"));
    emailUser.setCellValueFactory(new PropertyValueFactory<>("mail"));

    tableviewUser.setItems(filteredList);
}
     
     @FXML
    private void ModifierUser(ActionEvent event) throws IOException {
        Utilisateur user = tableviewUser.getSelectionModel().getSelectedItem();
       
        try{
         // Charger la nouvelle vue
            System.out.println("user.id::::"+user.getId_user());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUserFXML.fxml"));
        Parent root = loader.load();
       
       // Obtenir le contrôleur de la nouvelle vue
        ModifierUserFXMLController controleur = loader.getController();
             
        // Passer les donnees de l'utilisateur actuel au nouveau controleur
        controleur.setTextFields(user);

       
         // Afficher la nouvelle vue dans la fenêtre principale
        Scene scene = new Scene(root);
        Stage stage = (Stage) btn_modifier.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        showUsers();

        }catch(IOException e){
            System.out.println(e.getCause().getMessage());
        }
       
    }
   
    @FXML
    private void SupprimerUtilisateur(ActionEvent event) {
        Utilisateur user = tableviewUser.getSelectionModel().getSelectedItem();
        us.supprimerUtilisateur(user);
        showUsers();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Utilisateur supprimé");
                alert.showAndWait();  

    }
   
   
    private void searchRec() {
ObservableList<Utilisateur> list = us.afficherUtilisateurs();

    ObservableList<Utilisateur> filteredList = FXCollections.observableArrayList();

    for (Utilisateur user : list) {
        if (user.getRole().equals("Formateur") && user.getEtat().equals("actif")) {
            filteredList.add(user);
        } else if (!user.getRole().equals("Formateur")) {
            filteredList.add(user);
        }
    }    
   
   
    FilteredList<Utilisateur> filteredData = new FilteredList<>(filteredList, p -> true);

    // Set the items of your TableView to the filtered data
    tableviewUser.setItems(filteredData);

    // Add a listener to the text property of the TextField to detect changes in user input
    Recherche_User.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(user -> {
            // If the TextField is empty, show all items
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Convert the user input to lowercase
            String lowerCaseFilter = newValue.toLowerCase();

            // Define a Predicate to match the user input against the relevant columns in your TableView
            if (user.getNom().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Match on the nom column
            } else if (user.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Match on the prenom column
            } else if (user.getMail().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Match on the email column
            } else if (user.getLogin().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Match on the role column
            } else {
                return false; // No match
            }
        });
    });
}

    @FXML
    public void logout(){
        try{
                                        // Charger la nouvelle vue
                                       FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionFXML.fxml"));
                                       Parent root = loader.load();
                                        // Afficher la nouvelle vue dans la fenêtre principale
                                       Scene scene = new Scene(root);
                                       Stage stage = (Stage) btn_modifier.getScene().getWindow();
                                       stage.setScene(scene);
                                       stage.show();
                                       
                                       Utilisateur.setCurrent_User(new Utilisateur());

                                       }catch(IOException ex){
                                           System.out.println(ex.getCause().getMessage());
                                       }
    }
   
   
    private void makeFadeInTransition() {
    FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(AnchorPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
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
