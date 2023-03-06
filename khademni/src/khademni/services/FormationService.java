/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Formation;
import khademni.interfaces.IFormation;
import khademni.utils.MyConnection;

/**
 *
 * @author hmoud
 */
public class FormationService implements IFormation {

    Connection myconn =MyConnection.getInstance().getConnexion();
   
    @Override
    public void ajouterFormation(Formation f) {
        try {
            String sql = "insert into formation(id_user,titre, description, domaine_formation, prix)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, 1);
            ste.setString(2, f.getTitre());
            ste.setString(3, f.getDescription());
            ste.setString(4, f.getDomaine_formation());
            ste.setDouble(5, f.getPrix());
            ste.executeUpdate();
            System.out.println("Formation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    @Override
    public void supprimerFormation(Formation f) {
        try {
            String sql = "delete from formation where id_formation=?";
            PreparedStatement ste = myconn.prepareStatement(sql);
                ste.setInt(1,f.getId_formation());
                
            ste.executeUpdate();
            System.out.println("Formation supprimé !");            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public void modifierFormation(Formation f) {
        try {
            String sql = "update Formation set titre=?, description=?, domaine_formation=?, prix=? where id_formation=?";
            PreparedStatement ste = myconn.prepareStatement(sql);
                ste.setString(1,f.getTitre());
                ste.setString(2,f.getDescription());
                ste.setString(3,f.getDomaine_formation());
                ste.setDouble(4,f.getPrix());
                ste.setInt(5,f.getId_formation());
                
            ste.executeUpdate();
            System.out.println("Formation Modifié avec succés !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public ObservableList<Formation> afficherFormation() {
        
        ObservableList<Formation> formations = FXCollections.observableArrayList();
        
        try {
            String sql ="select * from formation";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                
                Formation f = new Formation(s.getInt(1), s.getString("description"), s.getString("titre"), s.getString("domaine_formation"), s.getDouble("prix"));
                formations.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
                        System.out.println("listeee::"+formations);

        return formations;
    }
    
    public ObservableList<Formation> getFormationsByFormateur(int idU) {
        
        ObservableList<Formation> formations = FXCollections.observableArrayList();
        
        try {
            String sql ="select * from formation where id_user=?";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, idU);
            ResultSet s = ste.executeQuery();
            
            while (s.next()) {
                
                Formation f = new Formation(s.getInt(1), s.getString("description"), s.getString("titre"), s.getString("domaine_formation"), s.getDouble("prix"));
                formations.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
                        System.out.println("listeee::"+formations);

        return formations;
    }
}