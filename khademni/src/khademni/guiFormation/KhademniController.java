/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package khademni.guiFormation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import khademni.entity.Formation;
import khademni.utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author hmoud
 */
public class KhademniController implements Initializable {

    @FXML
    private Button btnafficher;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnsupprimer;

    @FXML
    private TableColumn<?, ?> domainecolomn;

    @FXML
    private TableColumn<?, ?> idcolomn;

    @FXML
    private TableColumn<?, ?> prixcolomn;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> titrecolomn;

    @FXML
    private TextArea txtdescription;

    @FXML
    private TextField txtdomaine;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtprix;

    @FXML
    private TextField txttitre;

    @FXML
    void afficher(ActionEvent event) {       
      
    }

    @FXML
    void ajouter(ActionEvent event) {
        Connection myconn =MyConnection.getInstance().getConnexion();

        int id_formation;
        String titre,description,domaine_formation;
        float prix;
        id_formation = Integer.parseInt(txtid.getText());
        titre = txttitre.getText();
        description = txtdescription.getText();
        domaine_formation = txtdomaine.getText();
        prix = Float.parseFloat(txtprix.getText());
     try 
     {
        String sql = "insert into formation(titre, description, domaine_formation, prix)"
                    + "values (?,?,?,?)";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setString(1, titre);
        ste.setString(2,description);
        ste.setString(3, domaine_formation);
        ste.setFloat(4,prix);
        ste.executeUpdate();
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Formation Ajouté");

alert.setHeaderText("Formation Ajouté");
alert.setContentText("ajoutéééé !!!");

alert.showAndWait();
        
     //   table();
        
        txttitre.setText("");
        txtdescription.setText("");
        txtdomaine.setText("");
        txttitre.requestFocus();
     }
     catch(SQLException ex)
     {
         Logger.getLogger(KhademniController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
 /*   
    public void table()
    {
        Connect();
        ObservableList<Formation> formations = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("");
            ResultSet rs = pst.executeQuery();            
       {
        while(rs.next())
        {
           Formation ft = new Formation(); 
           ft.setTitre(rs.getString("titre"));
           ft.setDescription(rs.getString("description"));
           ft.setDomaine_formation(rs.getString("domaine"));
           formations.add(ft);
        }    
     }
                table.setItems(formations);
                titrecolomn.setCellValueFactory(f ->f.getValue().titreProperty());
                domainecolomn.setCellValueFactory(f -> f.getValue().domaineProperty());
                prixcolomn.setCellValueFactory(f -> f.getValue().prixProperty());
        
        
        
        }
        
        catch(SQLException ex)
        {
            Logger.getLogger(KhademniController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                 table.setRowFactory( tv -> {
       TableRow<Formation> myRow = new TableRow<>();
       myRow.setOnMouseClicked(event ->
       {
           if (event.getClickCount() == 1 && (!myRow.isEmpty()))
           {
                myIndex = table.getSelectionModel().getSelectedIndex();
               txtid = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
               txttitre.setText(table.getItems().get(myIndex).getTitre());
               txtdomaine.setText(table.getItems().get(myIndex).getDomaine());
               txtprix.setText(table.getItems().get(myIndex).getPrix());
            
           
           
           }
        });
            return myRow;
                 });
        }         
   */ 
    @FXML
    void supprimer(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
      //  table();    
    }    
  
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    
    
     public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studcruds","root","");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
