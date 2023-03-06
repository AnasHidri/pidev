/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.services;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Cours;
import khademni.interfaces.ICours;
import khademni.utils.MyConnection;



/**
 *
 * @author hmoud
 */
public class CoursService implements ICours{
    
    Connection myconn =MyConnection.getInstance().getConnexion();


    public void ajouterCours(Cours c,int id_f) {
        try {
            String sql = "insert into cours(titre,description,file,id_formation)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1,c.getTitre());
            ste.setString(2, c.getDescription());
            ste.setString(3, c.getFile());
            ste.setInt(4, id_f);
            ste.executeUpdate();
            System.out.println("Cours Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerCours(Cours c) {
        try {
            String sql = "delete from cours where id_cours=?";
            PreparedStatement ste = myconn.prepareStatement(sql);
                ste.setInt(1,c.getId_cours());
                
            ste.executeUpdate();
            System.out.println("Cours Supprimé !");            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void modifierCours(Cours c) {
        try {
            String sql = "update cours set titre=?, description=?, file=? where id_cours=?";
            PreparedStatement ste = myconn.prepareStatement(sql);
                ste.setString(1,c.getTitre());
                ste.setString(2, c.getDescription());
                ste.setString(3,c.getFile());
                ste.setInt(4,c.getId_cours());
                
            ste.executeUpdate();
            System.out.println("Cours Modifié avec succés !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Cours> afficherCours() {
        ObservableList<Cours> cours = FXCollections.observableArrayList();
        
        try {
            String sql ="select * from cours";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                Cours c = new Cours(s.getString("titre"),s.getString("description"),s.getString("file"),s.getInt(1));
                cours.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
                        System.out.println("listeee::"+cours);

        return cours;
    }
    
    public ObservableList<Cours> getCoursByFormation(int idf) {
        
        ObservableList<Cours> cours = FXCollections.observableArrayList();
        
        try {
            String sql ="select * from cours where id_formation=?";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, idf);
            ResultSet s = ste.executeQuery();
            
            while (s.next()) {
                
                Cours c = new Cours(s.getString("titre"), s.getString("description"), s.getString("file"), s.getInt(1));
                cours.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
                        System.out.println("listeee::"+cours);

        return cours;
    }
    
    
    public Cours getCoursByFormationId(int idf) {
        
        Cours cours = null;
        
        try {
            String sql ="select * from cours where id_formation=? limit 1";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, idf);
            ResultSet s = ste.executeQuery();
            
            if (s.next()) {
                cours = new Cours(s.getString("titre"), s.getString("description"), s.getString("file"), s.getInt("id_cours"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
        
        return cours;
    }
    

    @Override
    public void ajouterCours(Cours c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }