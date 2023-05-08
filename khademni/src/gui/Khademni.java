/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package gui;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Khademni extends Application {
    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("InscriptionFXML.fxml"));
           // Parent root = FXMLLoader.load(getClass().getResource("UsersListFXML.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("FormateurFXML.fxml"));
           // Parent root = FXMLLoader.load(getClass().getResource("DashboardFXML.fxml"));
           // Parent root = FXMLLoader.load(getClass().getResource("UsersListFXML.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("ActivationFormateurFXML.fxml"));

            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Khademni !");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
