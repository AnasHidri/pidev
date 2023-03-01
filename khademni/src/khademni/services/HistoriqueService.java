/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.services;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Historique;
import khademni.interfaces.IHistorique;
import khademni.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author mikea
 */
public class HistoriqueService implements IHistorique {

    Connection cnx;
      ObservableList<Historique> historique = FXCollections.observableArrayList(); 
        ObservableList<String> namedata = FXCollections.observableArrayList(); 
    
    public  HistoriqueService(){
         cnx = MyConnection.getInstance().getConnexion();
    }
    
    @Override
    public void ajouterHistorique(Historique h) {
      
               try {
            String sql = "insert into historique(id_user,date_action,action)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, h.getId_user());
            LocalDate today = LocalDate.now();
            ste.setDate(2, java.sql.Date.valueOf(today));
            ste.setString(3,h.getAction());
            ste.executeUpdate();
            System.out.println("Historique ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
               
    }

    @Override
    public ObservableList<Historique> afficherHistorique() {
        
         ObservableList<Historique> historique = FXCollections.observableArrayList(); 
         
           try {
            String sql = "select id_user,date_action,action from historique ";
            java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                  Historique u = new Historique(
                      s.getInt("id_user"),
                        s.getDate("date_action"),
                s.getString("action")
                      
                );
                historique.add(u);
             
             

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
         
         
        return  historique; 
    }
    
        public List<Historique> getColumn1Data() {
        return historique;
    }

    public ObservableList<String> getColumn2Data() {
        return namedata;
    }
        
    
     public ObservableList<String> afficherHistoriqueWithUser() {
        
         
           try {
            String sql = "select nom from historique,user where user.id_user=historique.id_user ";
            java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

               
             namedata.add(s.getString("nom"));
             System.out.println(namedata);
             

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
         
         
        return  namedata; 
    }
    
    
    
}
