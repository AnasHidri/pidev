/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiUser;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import khademni.entity.Client;
import khademni.entity.Formateur;
import khademni.entity.Utilisateur;
import khademni.gui.Navbar_Navigation;
import khademni.gui.PanierFXMLController;
import khademni.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProfileSettingsFXMLController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfdomaine;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfactuel;
    @FXML
    private TextField tfnouv;
    @FXML
    private TextField tfconfirm;
     @FXML
    private TextField tfimg;
    
    @FXML
    private Label login;
    
    @FXML
    private Button modifier_btn;
     @FXML
    private ImageView imageView;

    @FXML
    private Button selecimg;
    @FXML
    private Button modifier_btn2;
    @FXML
    private Button cv;
    @FXML
    private TextField tfcv;
    @FXML
    private Button certif;
    @FXML
    private TextField tfcertif;
    @FXML
    private Button openCV;
    @FXML
    private Button openCertif;
    @FXML
    private Button logout;
    @FXML
         private AnchorPane rootPane;
    
      private ComboBox<String> liste_for;
    
    @FXML
       private ComboBox<String> liste_ev;
    @FXML
            private ComboBox<String> liste_off;
    @FXML
            private ComboBox<String> pani;
    @FXML
            private Button prof;
    @FXML
    private AnchorPane idClient;
    @FXML
    private Button prof1;
    @FXML
    private ComboBox liste_for1;
    @FXML
    private ComboBox liste_off1;
    @FXML
    private ComboBox liste_ev1;
    @FXML
    private ComboBox pani1;
    @FXML
    private HBox cc;
    @FXML
    private AnchorPane idEmployeur;
    @FXML
    private AnchorPane idAdmin;
    @FXML
    private Button prof2;
    @FXML
    private ComboBox<?> liste_off2;
    @FXML
    private ComboBox<?> liste_ev2;
    @FXML
    private Button stat;
    @FXML
    private AnchorPane idFormateur;
    @FXML
    private Button prof3;
    @FXML
    private ComboBox<?> liste_for3;
    @FXML
    private ComboBox<?> liste_for2;
    @FXML
    private Label idsolde;
    @FXML
    private Label sold;
    @FXML
    private Button btnLesFormations1;
    @FXML
    private Button btnMesFormations;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           liste_for1.getItems().addAll("Liste formation");
            liste_ev1.getItems().addAll("Liste evenement","Mes participations");
            liste_off1.getItems().addAll("Liste offre","Mes candidatures");
            pani1.getItems().addAll("Mon panier","Mes formations");
            
            
            
liste_for1.setOnAction(event -> {
    String selectedPage = (String) liste_for1.getSelectionModel().getSelectedItem();

   if (selectedPage.equals("Liste formation")) {
        // navigate to Page 1
         Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/guiFormation/formationC.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});

liste_ev1.setOnAction(event -> {
    String selectedPage = (String) liste_ev1.getSelectionModel().getSelectedItem();

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

liste_off1.setOnAction(event -> {
    String selectedPage = (String) liste_off1.getSelectionModel().getSelectedItem();

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

pani1.setOnAction(event -> {
    String selectedPage = (String) pani1.getSelectionModel().getSelectedItem();

    if (selectedPage.equals("Mon panier")) {
           Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/gui/PanierFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else if (selectedPage.equals("Mes formations")) {
        Navbar_Navigation SC = new Navbar_Navigation();
       String ch= "/khademni/gui/MesFormationFXML.fxml";
        try {
            SC.naviger((ActionEvent) event, ch);
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
});
            
            
            
       rootPane.setOpacity(0);
       makeFadeInTransition();
        
        setTextFields();
        displayImage();
        
        if("Client".equals(Utilisateur.Current_User.getRole())){
            certif.setVisible(false);
            tfcertif.setVisible(false);
            openCertif.setVisible(false);
            idClient.setVisible(true);
            idsolde.setVisible(true);
            sold.setVisible(true);

            
            
        }else if("Formateur".equals(Utilisateur.Current_User.getRole())){
            cv.setVisible(false);
            tfcv.setVisible(false);
            openCV.setVisible(false);
            idFormateur.setVisible(true);

        }else if("Employeur".equals(Utilisateur.Current_User.getRole())){
            certif.setVisible(false);
            tfcertif.setVisible(false);
            cv.setVisible(false);
            tfcv.setVisible(false);
            openCV.setVisible(false);
            openCertif.setVisible(false);
            idEmployeur.setVisible(true);

        }else{
            certif.setVisible(false);
            tfcertif.setVisible(false);
            cv.setVisible(false);
            tfcv.setVisible(false);
            openCV.setVisible(false);
            openCertif.setVisible(false);
            idAdmin.setVisible(true);
        }
        
        
    //    Image image = new Image("C:\\Users\\ASUS\\Desktop\\IMG_1976.png");
   // imageView.setImage(image);
    }   
    
    UtilisateurService us = new UtilisateurService();
    
     public void setTextFields(){
        tfnom.setText(Utilisateur.Current_User.getNom());
        tfprenom.setText(Utilisateur.Current_User.getPrenom());
        tfdomaine.setText(Utilisateur.Current_User.getDomaine());
        tfmail.setText(Utilisateur.Current_User.getMail());
        login.setText(Utilisateur.Current_User.getLogin());
        tfimg.setText(Utilisateur.Current_User.getImage());
       
        if (Utilisateur.Current_User instanceof Client) {
            Client client = (Client) Utilisateur.Current_User;
            tfcv.setText(client.getCv());
            idsolde.setText(client.getSolde()+"");
        }else if(Utilisateur.Current_User instanceof Formateur){
            Formateur formateur = (Formateur) Utilisateur.Current_User;
            tfcertif.setText(formateur.getCertif());
        }
    }
    
     
     @FXML
    void selectImage(ActionEvent event) throws FileNotFoundException, SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String imagePath = selectedFile.getPath();
            // Save the imagePath in your database using an SQL query
            tfimg.setText(imagePath);
            us.updateImage(imagePath, Utilisateur.Current_User.getId_user());
            // Display the selected image in the ImageView control
            Image image = new Image(new FileInputStream(selectedFile));
            imageView.setImage(image);
        }
    }
    
     public void displayImage() {
    try {
        Image image = new Image(new FileInputStream(tfimg.getText()));
        imageView.setImage(image);
    } catch (FileNotFoundException e) {
        System.out.println("Image file not found: " + tfimg.getText());
    }
}
     
     @FXML
    void ModifierInfo(ActionEvent event) throws IOException {
         UtilisateurService us= new UtilisateurService();
         
         Integer id=Utilisateur.Current_User.getId_user();
         System.out.println("idd :::: "+id);
         String nom=tfnom.getText();
         String prenom=tfprenom.getText();
         String domaine=tfdomaine.getText();
         String mail=tfmail.getText();
         
         String mdp="";
         String role="";
         String etat="";

         if(       nom.isEmpty()
                    | prenom.isEmpty()
                    | domaine.isEmpty()
                    | mail.isEmpty()
                    ){
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs sont obligatoire !!");
                alert.showAndWait();
         }else if(!(nom.matches("[a-zA-Z]+") & prenom.matches("[a-zA-Z]+") )){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le nom et le prenom doivent contenir que des lettres !!");
                alert.showAndWait();    
            }else {
         Utilisateur user =new Utilisateur(id,nom,prenom,"",role,etat,mail,domaine,mdp);
         System.out.println(user.getDomaine());
         System.out.println("user :: "+user); 
           us.modifierProfil(user);
          
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Données modifiés");
                alert.showAndWait();  
         }
    }
    
    @FXML
    void ModifierPassword(ActionEvent event) throws IOException {
        
        UtilisateurService us= new UtilisateurService();
        
        Integer id=Utilisateur.Current_User.getId_user();
        
        String nom=tfnom.getText();
         String prenom=tfprenom.getText();
         String domaine=tfdomaine.getText();
         String mail=tfmail.getText();
         
         String role="";
         String etat="";
         
        String mdp=tfnouv.getText();

    if(Utilisateur.Current_User.getPassword().equals(tfactuel.getText()) && mdp.equals(tfconfirm.getText())){
        
           Utilisateur user =new Utilisateur(id,nom,prenom,"",role,etat,mail,domaine,mdp);
           us.modifierPassword(user);
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Mot de passe modifié");
                alert.showAndWait();  
        
    }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Erreur au niveau du mot de passe");
                alert.showAndWait();  
    }

                 
    }
    
        @FXML
    public void logout(){
        try{
                                        // Charger la nouvelle vue
                                       FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionFXML.fxml"));
                                       Parent root = loader.load();
                                        // Afficher la nouvelle vue dans la fenêtre principale
                                       Scene scene = new Scene(root);
                                       Stage stage = (Stage) cv.getScene().getWindow();
                                       stage.setScene(scene);
                                       stage.show();
                                       
                                       Utilisateur.setCurrent_User(new Utilisateur());

                                       }catch(IOException ex){
                                           System.out.println(ex.getCause().getMessage());
                                       }
    }
    
    
    public void selectCertif() throws SQLException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter(
        "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            tfcertif.setText(selectedFile.getPath());
            us.updateCv(tfcertif.getText(), Utilisateur.Current_User.getId_user());
            System.out.println("filepath::"+tfcertif.getText());
        }
    }
    
    @FXML
    public void selectCv() throws SQLException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter(
        "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            tfcv.setText(selectedFile.getPath());
            us.updateCv(tfcv.getText(), Utilisateur.Current_User.getId_user());

            System.out.println("filepath::"+tfcv.getText());
        }
    }
    
    @FXML
    public void openCV(){
 String cvPath = tfcv.getText(); // get file path from the database

// Create a new file object with the file path
File file = new File(cvPath);

// Check if the file exists and is a file
if (file.exists() && file.isFile()) {
    // Get the desktop object
    Desktop desktop = Desktop.getDesktop();

    try {
        // Open the file using the desktop object
        desktop.open(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
} else {
    // The file does not exist or is not a file
    System.out.println("File does not exist or is not a file.");
}
    }
    
    @FXML
    public void openCertif(){
 String certifPath = tfcertif.getText(); // get file path from the database

// Create a new file object with the file path
File file = new File(certifPath);

// Check if the file exists and is a file
if (file.exists() && file.isFile()) {
    // Get the desktop object
    Desktop desktop = Desktop.getDesktop();

    try {
        // Open the file using the desktop object
        desktop.open(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
} else {
    // The file does not exist or is not a file
    System.out.println("File does not exist or is not a file.");
}
    }

    private void makeFadeInTransition() {
    FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
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
         @FXML
    private void stat(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiUser/DashboardFXML.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
    
     @FXML
        private void LesFormations(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiFormation/Formation.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
        
              @FXML
        private void MesFormations(ActionEvent event)  throws IOException {
   
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/khademni/guiFormation/Formation.fxml"));
         Stage stage = new Stage();
         
         stage.setScene(new Scene(loader.load()));
         stage.show();
    Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    currentStage.hide();
   
   
   
}
     
}