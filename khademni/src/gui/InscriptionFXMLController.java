/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import khademni.entity.Client;
import khademni.entity.Employeur;
import khademni.entity.Formateur;
import khademni.entity.Utilisateur;
import khademni.utils.MyConnection;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InscriptionFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //employeur
    @FXML
    private TextField tfnom_emp;
    @FXML
    private TextField tfprenom_emp;
    @FXML
    private TextField tflogin_emp;
    @FXML
    private TextField tfpassword_emp;
    @FXML
    private TextField tfconfirm_password_emp;
    @FXML
    private TextField tfnomsoc_emp;
    @FXML
    private TextField tfdomaine_emp;
    @FXML
    private TextField tfemail_emp;
    @FXML
    private Button signup_btn_emp;
    @FXML
    private Hyperlink login_acc_emp;
    @FXML
    private Hyperlink Employeur_signup;
    @FXML
    private AnchorPane signup_form_employeur;
    
        //formateur
    @FXML
    private TextField tfnom_form;
    @FXML
    private TextField tfprenom_form;
    @FXML
    private TextField tflogin_form;
    @FXML
    private TextField tfpassword_form;
    @FXML
    private TextField tfconfirm_password_form;
    @FXML
    private TextField tfcertif_form;
    @FXML
    private TextField tfdomaine_form;
    @FXML
    private TextField tfemail_form;
    @FXML
    private Button signup_btn_form;
    @FXML
    private Hyperlink login_acc_form;
    @FXML
    private Hyperlink Formateur_signup;
    @FXML
    private AnchorPane signup_form_formateur;
        //client
    @FXML
    private TextField tfnom_cl;
    @FXML
    private TextField tfprenom_cl;
    @FXML
    private TextField tflogin_cl;
    @FXML
    private TextField tfpassword_cl;
    @FXML
    private TextField tfconfirm_password_cl;
    @FXML
    private TextField tfcv_cl;
    @FXML
    private TextField tfdomaine_cl;
    @FXML
    private TextField tfemail_cl;
    @FXML
    private Button signup_btn_cl;
    @FXML
    private Hyperlink login_acc_cl;
    @FXML
    private Hyperlink Client_signup;
    @FXML
    private AnchorPane signup_form_client;
    
        //login

    @FXML
    private Hyperlink create_acc;
    @FXML
    private AnchorPane login_form;
    @FXML
    private TextField login_signin;
    @FXML
    private TextField password_signin;
    
    @FXML
    private Button login_btn;
    
    
        //role form
    @FXML
    private AnchorPane role_form;
   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void exit(){
        System.exit(0);
    }
    
    UtilisateurService us = new UtilisateurService();
    Connection myconn =MyConnection.getInstance().getConnexion();

    
    
            
    public void changeForm(ActionEvent event){
        if(event.getSource() == create_acc){
            role_form.setVisible(true);
            login_form.setVisible(false);
        }else if(event.getSource()==login_acc_emp){
            login_form.setVisible(true);
            signup_form_employeur.setVisible(false);
        }
        else if(event.getSource()==login_acc_form){
            login_form.setVisible(true);
            signup_form_formateur.setVisible(false);
        }
        else if(event.getSource()==login_acc_cl){
            login_form.setVisible(true);
            signup_form_client.setVisible(false);
        }
        else if(event.getSource()==Employeur_signup){
            signup_form_employeur.setVisible(true);
            role_form.setVisible(false);
        }else if(event.getSource()==Formateur_signup){
            signup_form_formateur.setVisible(true);
            role_form.setVisible(false);
        }
        else if(event.getSource()==Client_signup){
            signup_form_client.setVisible(true);
            role_form.setVisible(false);
        }
    }
    
    public boolean ValidationEmail(String email){ 
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9._]+([.][a-zA-Z0-9]+)+");
        Matcher match = pattern.matcher(email);
        
        if(match.find() && match.group().equals(email))
        {
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText(null);
            alert.setContentText("Le format d'email n'est valide");
            alert.showAndWait();
            
            return false;
        }
    }
    
    private boolean userExist(String login ) throws SQLException {
        String query = "SELECT * FROM user WHERE login = ?";
        PreparedStatement statement = myconn.prepareStatement(query);
        statement.setString(1, login);
        ResultSet result = statement.executeQuery();
        return result.next();
    }
    
    @FXML
    public void selectCertif() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter(
        "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            tfcertif_form.setText(selectedFile.getPath());
            System.out.println("filepath::"+tfcertif_form.getText());
        }
    }
    
    @FXML
    public void selectCv() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter(
        "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            tfcv_cl.setText(selectedFile.getPath());
            System.out.println("filepath::"+tfcv_cl.getText());
        }
    }
    
    @FXML
    private void saveEmployeur(ActionEvent event) throws IOException {
            String nom = tfnom_emp.getText();
            String prenom = tfprenom_emp.getText();
            String login = tflogin_emp.getText();
            String password = tfpassword_emp.getText();
            String nom_soc = tfnomsoc_emp.getText();
            String mail = tfemail_emp.getText();
            String domaine = tfdomaine_emp.getText();
            String role="Employeur";
            String confirm_pasword=tfconfirm_password_emp.getText();

            
            try {
            
            if(       nom.isEmpty()
                    | prenom.isEmpty()
                    | login.isEmpty()
                    | password.isEmpty()
                    | confirm_pasword.isEmpty()
                    |nom_soc.isEmpty()
                    |mail.isEmpty()
                    |domaine.isEmpty()
                    ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs sont obligatoire !!");
                alert.showAndWait();
            }else if(confirm_pasword.length() < 8 | !confirm_pasword.equals(password) ){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe doit etre sup à 8 caractéres et le meme pour confirmer mot de passe !!");
                alert.showAndWait();    
            }else if(!(nom.matches("[a-zA-Z]+") & prenom.matches("[a-zA-Z]+") )){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le nom et le prenom doivent contenir que des lettres !!");
                alert.showAndWait();    
            }else if(userExist(login)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Travel Me :: Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("l'identifiant : "+login+" est deja utilise!!");
                                alert.showAndWait();
            }
            else{
                if(ValidationEmail(mail)){
                Employeur emp = new Employeur(nom, prenom, login, password, role, mail, domaine, nom_soc);
                us.ajouterEmployeur(emp);
                
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: BIENVENNUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous Etes Inscrit !!");
                alert.showAndWait();
                
                tfnom_emp.setText(null);
                tfprenom_emp.setText(null);
                tflogin_cl.setText(null);
                tfpassword_emp.setText(null);
                tfnomsoc_emp.setText(null);
                tfemail_emp.setText(null);
                tfdomaine_emp.setText(null);
                tfconfirm_password_emp.setText(null);
            
                
                login_form.setVisible(true);
                signup_form_employeur.setVisible(false);
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
        }

           
    }
    
    @FXML
    private void saveFormateur(ActionEvent event) throws IOException {
        String nom = tfnom_form.getText();
            String prenom = tfprenom_form.getText();
            String login = tflogin_form.getText();
            String password = tfpassword_form.getText();
            String certif = tfcertif_form.getText();
            String mail = tfemail_form.getText();
            String domaine = tfdomaine_form.getText();
            String role="Formateur";
            String confirm_pasword=tfconfirm_password_form.getText();
            
            try {
            
            if(       nom.isEmpty()
                    | prenom.isEmpty()
                    | login.isEmpty()
                    | password.isEmpty()
                    | confirm_pasword.isEmpty()
                    |certif.isEmpty()
                    |mail.isEmpty()
                    |domaine.isEmpty()
                    ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs sont obligatoire !!");
                alert.showAndWait();
            }else if(confirm_pasword.length() < 8 | !confirm_pasword.equals(password) ){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe doit etre sup à 8 caractéres et le meme pour confirmer mot de passe !!");
                alert.showAndWait();    
            }else if(!(nom.matches("[a-zA-Z]+") & prenom.matches("[a-zA-Z]+") )){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le nom et le prenom doivent contenir que des lettres !!");
                alert.showAndWait();    
            }else if(userExist(login)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Travel Me :: Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("l'identifiant : "+login+" est deja utilise!!");
                                alert.showAndWait();
            }
            else{
                if(ValidationEmail(mail)){
                Formateur f = new Formateur(nom, prenom, login, password, role, mail, domaine,certif);
                us.ajouterFormateur(f);
                                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: BIENVENNUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous Etes Inscrit !!");
                alert.showAndWait();
                
                tfnom_form.setText(null);
             tfprenom_form.setText(null);
            tflogin_form.setText(null);
            tfpassword_form.setText(null);
            tfcertif_form.setText("...");
            tfemail_form.setText(null);
             tfdomaine_form.setText(null);
            tfconfirm_password_form.setText(null);
            
                
                login_form.setVisible(true);
                signup_form_formateur.setVisible(false);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
        }  
    }
    
    
    
    @FXML
    private void saveClient(ActionEvent event) throws IOException {
        String nom = tfnom_cl.getText();
            String prenom = tfprenom_cl.getText();
            String login = tflogin_cl.getText();
            String password = tfpassword_cl.getText();
            String cv = tfcv_cl.getText();
            String mail = tfemail_cl.getText();
            String domaine = tfdomaine_cl.getText();
            String role="Client";
            float solde=1000;
            String confirm_pasword=tfconfirm_password_cl.getText();
            
            try {
            
            if(      nom.isEmpty()
                    | prenom.isEmpty()
                    | login.isEmpty()
                    | password.isEmpty()
                    | confirm_pasword.isEmpty()
                    |cv.isEmpty()
                    |mail.isEmpty()
                    |domaine.isEmpty()
                    ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Tous les champs sont obligatoire !!");
                alert.showAndWait();
            }else if(confirm_pasword.length() < 8 | !confirm_pasword.equals(password) ){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe doit etre sup à 8 caractéres et le meme pour confirmer mot de passe !!");
                alert.showAndWait();    
            }else if(!(nom.matches("[a-zA-Z]+") & prenom.matches("[a-zA-Z]+") )){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Khademni :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Le nom et le prenom doivent contenir que des lettres !!");
                alert.showAndWait();    
            }else if(userExist(login)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Travel Me :: Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("l'identifiant : "+login+" est deja utilise!!");
                                alert.showAndWait();
            }
            else{
                if(ValidationEmail(mail)){
                Client c = new Client(nom, prenom, login, password, role, mail, domaine,solde,cv);
                us.ajouterClient(c);
                                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: BIENVENNUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous Etes Inscrit !!");
                alert.showAndWait();
                
                
            tfnom_cl.setText(null);
            tfprenom_cl.setText(null);
            tflogin_cl.setText(null);
            tfpassword_cl.setText(null);
            tfcv_cl.setText("...");
            tfemail_cl.setText(null);
            tfdomaine_cl.setText(null);
            tfconfirm_password_cl.setText(null);
                
                login_form.setVisible(true);
                signup_form_client.setVisible(false);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
        }  
    }
        

    public void login() throws IOException{
        if(login_signin.getText().equals("yassineadmin") && password_signin.getText().equals("adminadmin") )
        {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setTitle("Travel Me :: Success Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Bienvenue Admin");
                     try{
         // Charger la nouvelle vue
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UsersListFxml.fxml"));
        Parent root = loader.load();

        
         // Afficher la nouvelle vue dans la fenêtre principale
        Scene scene = new Scene(root);
        Stage stage = (Stage) login_btn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        }catch(IOException e){
            System.out.println(e.getCause().getMessage());
        }                   
        }else {
            
            String query2="select * from user where login=?  and password=?";
            Connection myconn =MyConnection.getInstance().getConnexion();
           try{
              PreparedStatement smt = myconn.prepareStatement(query2);
       
               smt.setString(1,login_signin.getText());
               smt.setString(2,password_signin.getText());
               ResultSet rs= smt.executeQuery();
                if(rs.next()){
                    if("Employeur".equals(rs.getString("role"))){
                        if("inactif".equals(rs.getString("etat"))){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Travel Me :: Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Votre compte n'est pas actif");
                                alert.showAndWait();
                        }else{
                        Employeur e = new Employeur(rs.getInt(1),rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("password"), rs.getString("role"), rs.getString("mail"), rs.getString("domaine"), rs.getString("nom_societe"));
                        Utilisateur.setCurrent_User(e);
                        System.out.println("current user id ::"+Utilisateur.Current_User.getId_user());
                        System.out.println("current user :: "+Utilisateur.Current_User);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Travel Me :: Success Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Vous etes connecté");
                                alert.showAndWait();
                                login_btn.getScene().getWindow().hide();
                                
                                try{
                                        // Charger la nouvelle vue
                                       FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeurFxml.fxml"));
                                       Parent root = loader.load();
                                        // Afficher la nouvelle vue dans la fenêtre principale
                                       Scene scene = new Scene(root);
                                       Stage stage = (Stage) login_btn.getScene().getWindow();
                                       stage.setScene(scene);
                                       stage.show();

                                       }catch(IOException ex){
                                           System.out.println(ex.getCause().getMessage());
                                       }
                        }
                                

                    }
                     
                }else{
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password !!");
                alert.showAndWait();  
                }
          
      }catch(SQLException ex){
           System.out.println(ex.getMessage());
      }

            
        }
         
    }
    

    
}
