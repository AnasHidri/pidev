/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
     
    
  
    
}
