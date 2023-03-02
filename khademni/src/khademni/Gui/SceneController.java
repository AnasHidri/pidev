/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.Gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author CYBERLAND
 */
public class SceneController {
    private Stage stage;
    private Scene scene ;
    private Parent root;
    
    public void Scene1(ActionEvent event)throws IOException{
   Parent root = FXMLLoader.
                    load(getClass().getResource("OffreAdminFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
     public void Scene2(ActionEvent event)throws IOException{
         Parent root = FXMLLoader.
                    load(getClass().getResource("EmployeurFXML.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

      public void Scene3(ActionEvent event)throws IOException{
   Parent root = FXMLLoader.
                    load(getClass().getResource("OffreClientFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
      public void Scene4(ActionEvent event)throws IOException{
   Parent root = FXMLLoader.load(getClass().getResource("InterfacePrincipaleFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    
        public void Scene5(ActionEvent event)throws IOException{
   Parent root = FXMLLoader.load(getClass().getResource("ListOffreClientFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();}
     public void Scene6(ActionEvent event)throws IOException{
   Parent root = FXMLLoader.load(getClass().getResource("MesCandidatureClientFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

  
}
 public void Scene7(ActionEvent event)throws IOException{
   Parent root = FXMLLoader.load(getClass().getResource("CandidatureEmployeurFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();}}
 