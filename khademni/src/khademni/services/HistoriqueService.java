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

/**
 *
 * @author mikea
 */
public class HistoriqueService implements IHistorique {

    Connection cnx;
    
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
            ste.setString(2, h.getDate_action());
            ste.setString(3,h.getAction());
            ste.executeUpdate();
            System.out.println("Historique ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
               
    }

    @Override
    public ObservableList<Historique> afficherLigneCommande() {
        
         ObservableList<Historique> historique = FXCollections.observableArrayList(); 
         
           try {
            String sql = "select id_user,date_action,action from historique ";
            java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                  Historique u = new Historique(
                      s.getInt("id_user"),
                        s.getString("date_action"),
                s.getString("action")
                      
                );
                historique.add(u);
             
             

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
         
         
        return  historique; 
    }
    
}
