/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import api.MailService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;


import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.mail.MessagingException;
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
    private ComboBox tfdomaine_emp;
    @FXML
    private TextField tfemail_emp;
    
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
    private TextField tfemail_form;
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
    private TextField tfemail_cl;
    @FXML
    private Button signup_btn_cl;
    @FXML
    private Hyperlink login_acc_cl;
    @FXML
    private Hyperlink Client_signup;
    @FXML
    private AnchorPane signup_form_client;
    @FXML
    private AnchorPane side;
    
        //login

    @FXML
    private Hyperlink create_acc;
    @FXML
    private AnchorPane login_form;
    @FXML
    private TextField login_signin;
    @FXML
    private PasswordField password_signin;
    
    @FXML
    private Button login_btn;
    
    
        //role form
    @FXML
    private AnchorPane role_form;
    @FXML
    private ImageView ImageP;
    @FXML
    private ImageView logo;
    @FXML
    private Button signup_btn_emp;
      @FXML
    private ComboBox tfdomaine_form;
      @FXML
    private ComboBox tfdomaine_cl;
      
         @FXML
    private ComboBox tfrole;
      
      @FXML
         private AnchorPane rootPane2;
      
      @FXML
private ToggleButton show_password_toggle;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ShowPassword();
            tfdomaine_emp.getItems().addAll("Informatique","Cuisine","Management");
            tfdomaine_form.getItems().addAll("Informatique","Cuisine","Management");
            tfdomaine_cl.getItems().addAll("Informatique","Cuisine","Management");
            tfrole.getItems().addAll("Employeur","Formateur","Client");
            tfrole.setValue("Client");
            
              // Show the "Client" form initially
    role_form.setVisible(true);
    login_form.setVisible(false);
    signup_form_employeur.setVisible(false);
    signup_form_formateur.setVisible(false);
    signup_form_client.setVisible(true);
    side.setVisible(true);

    
            tfrole.setOnAction(this::handleRoleSelection);

    } 
    
    private void handleRoleSelection(Event event) {
        
    String selectedRole = tfrole.getValue().toString();

    switch (selectedRole) {
        case "Employeur":
            role_form.setVisible(true);
            login_form.setVisible(false);
            signup_form_employeur.setVisible(true);
            signup_form_formateur.setVisible(false);
            signup_form_client.setVisible(false);

            
            break;
        case "Formateur":
            role_form.setVisible(true);
            login_form.setVisible(false);
            signup_form_employeur.setVisible(false);
            signup_form_formateur.setVisible(true);
            signup_form_client.setVisible(false);            
            break;
        case "Client":
            role_form.setVisible(true);
            login_form.setVisible(false);
            signup_form_employeur.setVisible(false);
            signup_form_formateur.setVisible(false);
            signup_form_client.setVisible(true);
            break;
        default:
            break;
    }
}
    
    @FXML
    public void changeForm(ActionEvent event){
        if(event.getSource() == create_acc){
            role_form.setVisible(true);
            login_form.setVisible(false);
            side.setVisible(true);
            signup_form_client.setVisible(true);            
            
        }else if(event.getSource()==login_acc_emp){
            login_form.setVisible(true);
            signup_form_employeur.setVisible(false);
            role_form.setVisible(false);
            side.setVisible(false);


        }
        else if(event.getSource()==login_acc_form){
            login_form.setVisible(true);
            signup_form_formateur.setVisible(false);
            role_form.setVisible(false);
            side.setVisible(false);

        }
        else if(event.getSource()==login_acc_cl){
            login_form.setVisible(true);
            signup_form_client.setVisible(false);
                        role_form.setVisible(false);
                        side.setVisible(false);

        }
        
    }
    
    
    
    public void ShowPassword(){
                PasswordField passwordField = password_signin;
ToggleButton showPasswordToggle = show_password_toggle;

ImageView eyeIcon = new ImageView(new Image(getClass().getResourceAsStream("eye.png")));
    eyeIcon.setFitHeight(17);
    eyeIcon.setFitWidth(17);

ImageView noeyeIcon = new ImageView(new Image(getClass().getResourceAsStream("noeye.png")));
    noeyeIcon.setFitHeight(17);
    noeyeIcon.setFitWidth(17);    

        

// Set the eye icon
showPasswordToggle.setGraphic(eyeIcon);

// Show or hide the password when the toggle button is clicked
showPasswordToggle.setOnAction(event -> {
    if (showPasswordToggle.isSelected()) {
        passwordField.setPromptText(passwordField.getText());
        passwordField.setText("");
        showPasswordToggle.setGraphic(noeyeIcon);
    } else {
        passwordField.setText(passwordField.getPromptText());
        passwordField.setPromptText("");
        showPasswordToggle.setGraphic(eyeIcon);
    }
});
    
    }
    @FXML
    public void exit(){
        System.exit(0);
    }
    
    UtilisateurService us = new UtilisateurService();

            
    
    
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
    
    public void SendMail(String destination, String subject, String body) throws GeneralSecurityException{        
    try {
                // MailService.sendEmail("ymahfoudh55@gmail.com", "test","bonjour");
                 MailService.sendEmail(destination, subject,body);
        } catch (MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void saveEmployeur(ActionEvent event) throws IOException, GeneralSecurityException {
            String nom = tfnom_emp.getText();
            String prenom = tfprenom_emp.getText();
            String login = tflogin_emp.getText();
            String password = tfpassword_emp.getText();
            String nom_soc = tfnomsoc_emp.getText();
            String mail = tfemail_emp.getText();
            //String domaine = tfdomaine_emp.getText();
            String domaine = (String) tfdomaine_emp.getSelectionModel().getSelectedItem();
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
            }else if(us.userExist(login)){
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
                    System.out.println("emp ajouté");
                
                    
                        SendMail(emp.getMail(), "Inscription", "Bonjour, vous étes bien inscrit sur notre application Khadamni.\n  Votre login : "+emp.getLogin()+"\n votre mot de passe : "+emp.getPassword());
                        SendMail("khademni.serviceClient@gmail.com", "Nouvelle inscription", "Bonjour, un nouvel utilisateur  sous le login : "+emp.getLogin()+" ,est inscrit sur l'application avec le role employeur.");
                     
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Khademni :: BIENVENNUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous Etes Inscrit !!");
                alert.showAndWait();
                
                tfnom_emp.setText(null);
                tfprenom_emp.setText(null);
                tflogin_emp.setText(null);
                tfpassword_emp.setText(null);
                tfnomsoc_emp.setText(null);
                tfemail_emp.setText(null);
                tfdomaine_emp.setPromptText("Choisir role");
                tfconfirm_password_emp.setText(null);
            
                
                login_form.setVisible(true);
                signup_form_employeur.setVisible(false);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
        }

           
    }
    
    @FXML
    private void saveFormateur(ActionEvent event) throws IOException, GeneralSecurityException {
        String nom = tfnom_form.getText();
            String prenom = tfprenom_form.getText();
            String login = tflogin_form.getText();
            String password = tfpassword_form.getText();
            String certif = tfcertif_form.getText();
            String mail = tfemail_form.getText();
            String domaine = (String) tfdomaine_form.getSelectionModel().getSelectedItem();
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
            }else if(us.userExist(login)){
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
                
                
                
                SendMail(f.getMail(), "Inscription", "Bonjour, vous étes bien inscrit sur notre application Khadamni.\n Veuillez attendez l'activation de votre compte par l'administrateur pour y acceder.\n Votre login : "+f.getLogin()+"\n votre mot de passe : "+f.getPassword());
                SendMail("khademni.serviceClient@gmail.com", "Nouvelle inscription", "Bonjour, un nouvel utilisateur  sous le login : "+f.getLogin()+" ,est inscrit sur l'application avec le role formateur. Veuillez activer son compte.");
                
                                
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
                tfdomaine_form.setPromptText("Choisir role");
            tfconfirm_password_form.setText(null);
            
                
                login_form.setVisible(true);
                signup_form_formateur.setVisible(false);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
        }  
    }
    
    
    
    @FXML
    private void saveClient(ActionEvent event) throws IOException, GeneralSecurityException {
        String nom = tfnom_cl.getText();
            String prenom = tfprenom_cl.getText();
            String login = tflogin_cl.getText();
            String password = tfpassword_cl.getText();
            String cv = tfcv_cl.getText();
            String mail = tfemail_cl.getText();
            String domaine = (String) tfdomaine_cl.getSelectionModel().getSelectedItem();
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
            }else if(us.userExist(login)){
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
                
                SendMail(c.getMail(), "Inscription", "Bonjour, vous étes bien inscrit sur notre application Khadamni.\n Votre login : "+c.getLogin()+"\n votre mot de passe : "+c.getPassword());
                SendMail("khademni.serviceClient@gmail.com", "Nouvelle inscription", "Bonjour, un nouvel utilisateur  sous le login : "+c.getLogin()+" ,est inscrit sur l'application avec le role client.");
                                
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
            tfdomaine_cl.setPromptText("Choisir role");
            tfconfirm_password_cl.setText(null);
                
                login_form.setVisible(true);
                signup_form_client.setVisible(false);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
        }  
    }
        
   
    
    
//    public void makeFadeOut(String path) {
//    FadeTransition fadeTransition = new FadeTransition();
//    fadeTransition.setDuration(Duration.millis(500));
//    fadeTransition.setNode(rootPane2);
//    fadeTransition.setFromValue(1);
//    fadeTransition.setToValue(0);
//    fadeTransition.setOnFinished((t) -> {
//        try {
//            loadNextScene(path);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    });
//
//    TranslateTransition translateTransition = new TranslateTransition();
//    translateTransition.setDuration(Duration.millis(500));
//    translateTransition.setNode(rootPane2);
//    translateTransition.setFromY(0);
//    translateTransition.setToY(-40);
//    translateTransition.setCycleCount(3);
//    translateTransition.setAutoReverse(true);
//
//    ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
//    parallelTransition.play();
//}
   
    public void makeFadeOut(String path) {
    FadeTransition fadeTransition = new FadeTransition();
    fadeTransition.setDuration(Duration.millis(500));
    fadeTransition.setNode(rootPane2);
    fadeTransition.setFromValue(1);
    fadeTransition.setToValue(0);
    fadeTransition.setOnFinished((t) -> {
        try {
            loadNextScene(path);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });

    TranslateTransition translateTransition = new TranslateTransition();
    translateTransition.setDuration(Duration.millis(500));
    translateTransition.setNode(rootPane2);
    translateTransition.setFromX(0);
    translateTransition.setToX(40);
    translateTransition.setCycleCount(3);
    translateTransition.setAutoReverse(true);

    ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
    parallelTransition.play();
}

    
    
    public void loadNextScene(String path) throws IOException{
  
         // Charger la nouvelle vue
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        
         // Afficher la nouvelle vue dans la fenêtre principale
        Scene scene = new Scene(root);
        Stage stage = (Stage) rootPane2.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        
    }
    

    @FXML
    public void login() throws IOException{
        if(login_signin.getText().equals("yassineadmin") && password_signin.getText().equals("adminadmin") )
        {
                
                     try{
     
        
        makeFadeOut("UsersListFxml.fxml");


        }catch(Exception e){
            System.out.println(e.getCause().getMessage());
        }                   
        }else {
            Utilisateur u = us.findUserByLogin(login_signin.getText(),password_signin.getText());
                if(u!=null){
                    if("Employeur".equals(u.getRole())){
                       
                        Employeur emp = (Employeur) u;
                        //Employeur e = new Employeur(u.getId_user(),u.getNom(),u.getPrenom() , u.getLogin(), u.getPassword(), u.getRole(), u.getMail(), u.getDomaine(), u.get,rs.getString("etat"),rs.getString("image"));
                        Utilisateur.setCurrent_User(emp);
                      
                                
                                try{    
                                            makeFadeOut("ProfileSettingsFXML.fxml");


                                       }catch(Exception ex){
                                           System.out.println(ex.getCause().getMessage());
                                       }

                    }else if("Formateur".equals(u.getRole())){
                        if("inactif".equals(u.getEtat())){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Travel Me :: Error Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Votre compte n'est pas actif");
                                alert.showAndWait();
                        }else{
                        Formateur form = (Formateur) u;

                  
                        Utilisateur.setCurrent_User(form);
             
                                try{
                                      
                                       makeFadeOut("ProfileSettingsFXML.fxml");

                                       }catch(Exception ex){
                                           System.out.println(ex.getCause().getMessage());
                                       }
                        }
                                

                    }else if("Client".equals(u.getRole())){
                        
                            Client cl = (Client) u;
                        Utilisateur.setCurrent_User(cl);
                        
                        
                                try{
                                      
                                       makeFadeOut("ProfileSettingsFXML.fxml");


                                       }catch(Exception ex){
                                           System.out.println(ex.getCause().getMessage());
                                       }


                    }
                     
                }else{
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password !!");
                alert.showAndWait();  
                }
          
      }     
        }
         
    }

    

    

