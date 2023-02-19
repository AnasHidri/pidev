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
import khademni.entity.Evenement;
import khademni.entity.Participation;
import khademni.interfaces.IParticipation;
import khademni.utils.MyConnection;

/**
 *
 * @author user
 */
public class ParticipationService implements IParticipation<Participation> {
    
    Connection cnx;

    public ParticipationService() {
        cnx = MyConnection.getInstance().getConnexion();
    }
    
    @Override
    public void ajouterParticipation(Participation p) {
         try {
            String sql = "insert into participation(id_evenement,id_user,status)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId_evenement());
            ste.setInt(2, p.getId_user());
            ste.setString(3, p.getStatus());
            ste.executeUpdate();
            System.out.println("Participation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimerParticipation(Evenement e) {
        String sql = "delete from participation where id_evenement=? and id_user="+2;
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, e.getId_evenement());
            ste.executeUpdate();
            System.out.println("Participation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierParticipation(String status, Participation p) {
        String sql = "update participation set status=? where id_participation=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, status);
            ste.setInt(2,p.getId_participation());
            ste.executeUpdate();
            System.out.println("participation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Participation> getAll() {
         List<Participation> participations = new ArrayList<>();
        try {
            String sql = "select * from participation";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Participation p = new Participation(s.getInt(2), s.getInt(3), 
                        s.getString("status"));
                participations.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return participations;
    }
    
    
    }
    

