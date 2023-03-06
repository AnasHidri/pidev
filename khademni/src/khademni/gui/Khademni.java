/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package khademni.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


/**
 *
 * @author hmoud
 */
public class Khademni extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
          //Parent root = FXMLLoader.load(getClass().getResource("add.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("Formation.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("modifier.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("formationC.fxml"));
          Parent root = FXMLLoader.load(getClass().getResource("graph.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("addcours.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("cours.fxml"));
          //Parent root = FXMLLoader.load(getClass().getResource("modifierC.fxml"));
          
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Khademni !");
            primaryStage.setScene(scene);
            primaryStage.show();  
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public static void main(String[] args) {
        launch(args);
    }
    
}
