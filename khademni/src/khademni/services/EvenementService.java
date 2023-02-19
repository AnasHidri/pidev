/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.services;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.scene.control.DatePicker;
import khademni.entity.Evenement;
import khademni.interfaces.IEvenement;
import khademni.utils.MyConnection;

/**
 *
 * @author user
 */
public class EvenementService implements IEvenement<Evenement> {

    Connection cnx;

    public EvenementService() {
        cnx = MyConnection.getInstance().getConnexion();
    }
    
    //DatePicker datePicker = new DatePicker();
     
           
    
    @Override
    public void ajouterEvenement(Evenement e) {
         try {
            String sql = "insert into evenement(id_user,titre,description,nom_societe,lieu)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, e.getId_user());
             //ste.setString(2,e.getDate_debut());
             //ste.setDate(3,  new java.sql.Date(e.getDate_fin().getTime()));
            ste.setString(2, e.getTitre());
            ste.setString(3, e.getDescription());
            ste.setString(4, e.getNom_societe());
            ste.setString(5, e.getLieu());
            ste.executeUpdate();
            System.out.println("Evenement ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    @Override
    public void supprimerEvenement(Evenement e) {
          String sql = "delete from evenement where id_evenement=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, e.getId_evenement());
            ste.executeUpdate();
            System.out.println("Evenement supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
    
    @Override
    public ObservableList<Evenement> getAll() {
        ObservableList<Evenement> evenements = FXCollections.observableArrayList();
        try {
            String sql = "select * from evenement";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Evenement e = new Evenement(s.getInt(1),s.getInt(2), 
                   s.getString("titre"), s.getString("description"), s.getString("nom_societe"), s.getString("lieu"));
                evenements.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }

    @Override
    public void modifierEvenement(String titre, String description, String lieu, Evenement e) {
        String sql = "update evenement set titre=?, description=?, lieu=? where id_evenement=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, titre);
            ste.setString(2, description);
            ste.setString(3, lieu);
            ste.setInt(4, e.getId_evenement());
           // ste.setInt(2,e.getId_evenement());
            ste.executeUpdate();
            System.out.println("Evenement modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    }
    

    
    

