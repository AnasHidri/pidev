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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import khademni.entity.Formation;
import khademni.utils.MyConnection;

/**
 *
 * @author hmoud
 */
public class FormationService implements InterfaceService {

    Connection myconn =MyConnection.getInstance().getConnexion();
   
    @Override
    public void ajouterFormation(Formation t) {
        try {
            String sql = "insert into formation(titre, description, domaine_formation, prix)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1, t.getTitre());
            ste.setString(2, t.getDescription());
            ste.setString(3, t.getDomaine_formation());
            ste.setFloat(4, t.getPrix());
            ste.executeUpdate();
            System.out.println("Formation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
/*    
    @Override
    public void supprimerFormation(Formation f1) {
        String sql = "delete from formation where id_formation=?";
        try {
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, f1.getId_formation());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    }

    public void modifierFormation(String titre,Formation f) {
        String sql = "update Formation set titre=? where id_formation=?";
        try {
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1,titre);
            ste.setInt(2,f.getId_formation());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public List<Formation> afficherFormation() {
        List<Formation> formations = new ArrayList<>();
        try {
            String sql = "select * from formation";
            PreparedStatement ste = myconn.prepareStatement(sql);
            // Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Formation f = new Formation("description", "titre","domaine_formation",10);
                formations.add(f);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return formations;    
    }


    @Override
    public List<Formation> getAll() {
        List<Formation> formations = new ArrayList<>();
        try {
            String sql = "select * from formation";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Formation f = new Formation("descritpion","titre","domaine_formation",0);
                formations.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return formations;    }
*/   
}