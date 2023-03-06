/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiFormation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import khademni.entity.Cours;
import khademni.entity.Formation;
import khademni.services.CoursService;
import khademni.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class AddcoursController implements Initializable {
    

    @FXML
    private TextField filePathTextField;
    
     @FXML
    private TextField id_f;
    
    @FXML
    private TextField txttitre;
    @FXML
    private TextArea txtdescription;


    private File selectedFile;
    
    CoursService cs = new CoursService();
    @FXML
    private Button btnAjouter;
    
    @FXML
    void chooseFile() {  
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*.pdf"),
                new ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mov"),
                new ExtensionFilter("All Files", "*.*"));
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePathTextField.setText(selectedFile.getPath());
        }
    }
    
     public void setTextFields(Formation f){
        id_f.setText(String.valueOf(f.getId_formation()));
          
    }

    @FXML
    void addFile() {
        // Récupérer le chemin du fichier
    String filePath = filePathTextField.getText();
    if (filePath != null && !filePath.isEmpty()) {
        // Récupérer le titre & description du fichier
        String[] fileParts = filePath.split("\\\\");
        String fileName = fileParts[fileParts.length - 1];
        String titre = txttitre.getText();
        String description = txtdescription.getText();
        // Récupérer la date courante
        LocalDate currentDate = LocalDate.now();
        // recuperer id formation 
        int idf=Integer.valueOf(id_f.getText());
        // Créer un objet Cours avec les informations du fichier
        Cours cours = new Cours(idf, filePathTextField.getText(), titre, description);
        // Sauvegarder le cours dans la base de données
        cs.ajouterCours(cours, idf);
        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormationController.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnAjouter.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        */
        // Afficher un message de confirmation
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout de cours");
        alert.setHeaderText("Cours ajouté avec succès !");
        alert.showAndWait();
        } else {
        // Afficher un message d'erreur si aucun fichier n'a été sélectionné
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ajout de cours");
        alert.setHeaderText("Erreur : Aucun fichier sélectionné !");
        alert.showAndWait();
        
        
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        filePathTextField.setText("No file selected");
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