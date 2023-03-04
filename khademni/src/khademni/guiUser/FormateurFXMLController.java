/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiUser;

import khademni.api.MailService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.mail.MessagingException;



/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormateurFXMLController implements Initializable {
    
    

    
         @FXML
    private Button pdf;
         @FXML 
    private ToggleButton tbn;
         
         @FXML
    private Button btnmail;
         @FXML
         private AnchorPane rootPane;
         
     /* 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
    
   @FXML
    private TextField filePath;

    public void selectFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter(
        "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath.setText(selectedFile.getPath());
            System.out.println("filepath::"+filePath.getText());
        }
    }
    
    public void openFile(){
 String filePath2 = filePath.getText(); // get file path from the database

// Create a new file object with the file path
File file = new File(filePath2);

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
public void SendMail() throws GeneralSecurityException{
    
    try {
                 MailService.sendEmail("ymahfoudh55@gmail.com", "test","bonjour");
                 //MailService.sendEmail("oueslati.yassmine1@gmail.com", "test","bonjour");
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
           
}
   

@FXML
    public void handleButtonClick(){
        makeFadeOut("DashboardFXML.fxml");
        //t.makeFadeOut(rootPane,pdf);
    }
    
    
    public void makeFadeOut(String path){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((t) -> {
            try {
                loadNextScene(path);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } );
        fadeTransition.play();
    }
    
    
    public void loadNextScene(String path) throws IOException{
  
         // Charger la nouvelle vue
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        
         // Afficher la nouvelle vue dans la fenêtre principale
        Scene scene = new Scene(root);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        
    }


}
