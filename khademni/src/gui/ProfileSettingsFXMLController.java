/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import khademni.entity.Utilisateur;

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
    private Label login;
    
    @FXML
    private Button modifier_btn;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTextFields();
    }   
    
     public void setTextFields(){
        tfnom.setText(Utilisateur.Current_User.getNom());
        tfprenom.setText(Utilisateur.Current_User.getPrenom());
        tfdomaine.setText(Utilisateur.Current_User.getDomaine());
        tfmail.setText(Utilisateur.Current_User.getMail());
        login.setText(Utilisateur.Current_User.getLogin());
    }
    
}
