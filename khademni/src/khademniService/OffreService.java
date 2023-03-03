  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademniService;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import khademni.entity.Offre;
import khademni.interfaces.IOffre;
import khademni.utils.MyConnection;
//import khademni.interfaces.IOffre;

/**
 *
 * @author CYBERLANDo
 */
public class OffreService implements IOffre   {
       Connection myconn =MyConnection.getInstance().getmyconn();

    @Override

 public void ajouterOffre(Offre O){
         try {
              
            String sql = "INSERT INTO offre(id_user,titre,description,adresse_societe,domaine_offre,date_debut,date_limite)"
                    + "VALUES (?,?,?,?,?,?,?)";
            
            PreparedStatement ste = myconn.prepareStatement(sql);
           
            ste.setInt(1, O.getId_user());
            ste.setString(2, O.getTitre());
            ste.setString(3, O.getDescription());
            ste.setString(4, O.getAdresse_societe());
            ste.setString(5, O.getDomaine_offre());
            ste.setDate(6, O.getDate_debut());
            ste.setDate(7, O.getDate_limite());
         

            ste.executeUpdate();
            System.out.println(" Offre ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    @Override
    public void modifierOffre(Offre O) {
 String sql="update offre set   titre=?, description=?,adresse_societe=?,domaine_offre=?,date_debut=?,date_limite=?  where  id_user= ? and id_offre=? ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
             
                ste.setString(1,O.getTitre());
                ste.setString(2, O.getDescription());
                ste.setString(3, O.getAdresse_societe());
                ste.setString(4, O.getDomaine_offre());
                ste.setDate(5, O.getDate_debut());
                ste.setDate(6, O.getDate_limite());
                ste.setInt(7, O.getId_user());
                 ste.setInt(8, O.getId_offre());
            ste.executeUpdate();
            System.out.println("Offre modifiée");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       }

       @Override


    
    public void supprimerOffre(Offre O) {
         String sql = "delete  from offre where id_offre=?  ";
        try {
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, O.getId_offre());
              
            ste.executeUpdate();
            System.out.println("Offre supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
       @Override
    public ObservableList<Offre> afficherOffre() {
       
        ObservableList<Offre> OffreList = FXCollections.observableArrayList();

        try {
            String sql = "select * from offre  ";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Offre o = new Offre(s.getInt(1),
                       s.getInt(2),
                       s.getString("titre"),
                       s.getString("description"),
                       s.getString("adresse_societe"),
                       s.getString("domaine_offre"),
                       s.getDate(7),
                        
                       s.getDate(8),
                       s.getString("etat"));

                       OffreList.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return OffreList;
    }

    public void RefuseOffre(Offre O) {
 String sql="update offre set etat='refuse' where id_offre=?  ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
             
                 ste.setInt(1, O.getId_offre());
            ste.executeUpdate();
            System.out.println("Offre Réfusée");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       }
  public ObservableList<Offre> afficherattenteOffre() {
  ObservableList<Offre> OffreList = FXCollections.observableArrayList();

        try {
            String sql = "select * from offre where offre.etat='en attente'";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Offre o = new Offre(s.getInt(1),
                       s.getInt(2),
                       s.getString("titre"),
                       s.getString("description"),
                       s.getString("adresse_societe"),
                       s.getString("domaine_offre"),
                       s.getDate("date_debut"),
                        
                       s.getDate("date_limite"),
                       s.getString("etat"));

                       OffreList.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return OffreList;
    }

    public void accepterOffre(Offre O) {
 String sql="update offre set etat='accepter'  where id_offre=?  ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
             
               
                 ste.setInt(1, O.getId_offre());
            ste.executeUpdate();
            System.out.println("Offre Accepter");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       }
    public ObservableList<Offre> afficherAccepterOffre() {
       
        ObservableList<Offre> OffreList = FXCollections.observableArrayList();

        try {
            String sql = "select * from offre where offre.etat='accepter'";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Offre o = new Offre(s.getInt(1),
                       s.getInt(2),
                       s.getString("titre"),
                       s.getString("description"),
                       s.getString("adresse_societe"),
                       s.getString("domaine_offre"),
                       s.getDate(7),
                        
                       s.getDate(8),
                       s.getString("etat"));

                       OffreList.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return OffreList;
    }

  
    

  

public ObservableList<Offre> getOffresByDate(LocalDate date_debut, LocalDate date_limite) {
    ObservableList<Offre> offres = FXCollections.observableArrayList();
    try {
        String sql = "SELECT * FROM offre WHERE date_debut >= ? AND date_limite <= ? AND offre.etat='en attente'";
        PreparedStatement ste = myconn.prepareStatement(sql);
        ste.setDate(1, Date.valueOf(date_debut));
        ste.setDate(2, Date.valueOf(date_limite));
        ResultSet s = ste.executeQuery();
        while (s.next()) {
            Offre o = new Offre(s.getInt(1),
                   s.getInt(2),
                   s.getString("titre"),
                   s.getString("description"),
                   s.getString("adresse_societe"),
                   s.getString("domaine_offre"),
                   s.getDate(7),
                        
                       s.getDate(8),
                   s.getString("etat"));
            offres.add(o);
        }
        return offres;
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return null;
    
    }
      
}
}
