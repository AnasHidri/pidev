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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.scene.control.DatePicker;
import khademni.entity.Evenement;
import khademni.interfaces.IEvenement;
import khademni.utils.MyConnection;
import java.sql.Date;

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
            String sql = "insert into evenement(id_user,date_debut,date_fin,titre,description,nom_societe,lieu)"
                    + "values (?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, e.getId_user());
             
            
             ste.setDate(2, e.getDate_debut());
            ste.setDate(3, e.getDate_fin());
            ste.setString(4, e.getTitre());
            ste.setString(5, e.getDescription());
            ste.setString(6, e.getNom_societe());
            ste.setString(7, e.getLieu());
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

                Evenement e = new Evenement(s.getInt(1),s.getInt(2), s.getDate(3),s.getDate(4),
                   s.getString("titre"), s.getString("description"), s.getString("nom_societe"), s.getString("lieu"));
                evenements.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }

    @Override
    public void modifierEvenement(Date date_debut, Date date_fin,String titre, String description, String lieu, Evenement e) {
        String sql = "update evenement set date_debut=?, date_fin=?, titre=?, description=?, lieu=? where id_evenement=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setDate(1, date_debut);
            ste.setDate(2, date_fin);
            ste.setString(3, titre);
            ste.setString(4, description);
            ste.setString(5, lieu);
             ste.setInt(6, e.getId_evenement());
           // ste.setInt(2,e.getId_evenement());
            ste.executeUpdate();
            System.out.println("Evenement modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     
   public ObservableList<Evenement> MaListe() {
         
         ObservableList<Evenement> evenements = FXCollections.observableArrayList();
          
        try {
            String query = "select evenement.id_evenement, evenement.id_user, evenement.date_debut, evenement.date_fin, evenement.titre, evenement.description, evenement.nom_societe"
                    + ", evenement.lieu from evenement,participation where evenement.id_evenement=participation.id_evenement and participation.id_user="+11;
             PreparedStatement ste = cnx.prepareStatement(query);
              ResultSet s = ste.executeQuery();
              while(s.next()){
                     Evenement e = new Evenement(s.getInt(1),s.getInt(2),s.getDate(3),s.getDate(4), 
                   s.getString("titre"), s.getString("description"), s.getString("nom_societe"), s.getString("lieu"));
                evenements.add(e);
                e.toString();
              }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
      
       return evenements;
    
    }
   
    
    
  public ObservableList<Evenement> getBetweenDates(Date date_debut, Date date_fin) {
    ObservableList<Evenement> list = FXCollections.observableArrayList();
    String query = "SELECT * FROM evenement WHERE date_debut >= ? AND date_fin <= ?";
    try {
        PreparedStatement ste = cnx.prepareStatement(query);
        ste.setDate(1, date_debut);
        ste.setDate(2, date_fin);
        ResultSet s = ste.executeQuery();
        while (s.next()) {
            Evenement e = new Evenement(s.getInt(2), s.getDate(3), s.getDate(4), 
                   s.getString("titre"), s.getString("description"), s.getString("nom_societe"), s.getString("lieu"));
            
            list.add(e);
            System.out.println("testttt");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return list;
}
    
  public ObservableList<Evenement> Stat() {
    ObservableList<Evenement> list = FXCollections.observableArrayList();
    try {
        String sql = "SELECT e.id_evenement, e.id_user, e.date_debut, e.date_fin, e.titre, e.description, e.nom_societe, e.lieu " +
                     "FROM evenement e " +
                     "JOIN participation p ON e.id_evenement = p.id_evenement " +
                     "GROUP BY e.id_evenement " +
                     "ORDER BY COUNT(p.id_user) DESC " +
                     "LIMIT 3";
        PreparedStatement statement = cnx.prepareStatement(sql);
        ResultSet s = statement.executeQuery();

        // Récupération des valeurs de chaque ligne de résultat
        while (s.next()) {
            int eventId = s.getInt("id_evenement");
            int userId = s.getInt("id_user");
            Date startDate = s.getDate("date_debut");
            Date endDate = s.getDate("date_fin");
            String eventTitle = s.getString("titre");
            String description = s.getString("description");
            String nomSociete = s.getString("nom_societe");
            String lieu = s.getString("lieu");
            Evenement e = new Evenement(eventId, userId, startDate, endDate, eventTitle, description, nomSociete, lieu);
            list.add(e);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}
  
  public ObservableList<Evenement> getMostLikedEvents() {
    ObservableList<Evenement> list = FXCollections.observableArrayList();
    try {
        String sql = "SELECT e.id_evenement, e.id_user, e.date_debut, e.date_fin, e.titre, e.description, e.nom_societe, e.lieu, COUNT(p.vote) AS likes " +
                     "FROM evenement e " +
                     "JOIN participation p ON e.id_evenement = p.id_evenement " +
                     "WHERE p.vote = 1 " +
                     "GROUP BY e.id_evenement " +
                     "ORDER BY likes DESC " +
                     "LIMIT 3";
        PreparedStatement statement = cnx.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int idEvenement = rs.getInt("id_evenement");
            int idUser = rs.getInt("id_user");
            Date dateDebut = rs.getDate("date_debut");
            Date dateFin = rs.getDate("date_fin");
            String titre = rs.getString("titre");
            String description = rs.getString("description");
            String nomSociete = rs.getString("nom_societe");
            String lieu = rs.getString("lieu");
           // int likes = rs.getInt("likes");
            Evenement evenement = new Evenement(idEvenement, idUser, dateDebut, dateFin, titre, description, nomSociete, lieu);
            //evenement.setLikes(likes);
            list.add(evenement);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}
  
  
} 

    
    
