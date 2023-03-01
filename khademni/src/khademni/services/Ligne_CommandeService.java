/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.services;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import khademni.entity.Ligne_commande;
import khademni.interfaces.ILigne_commande;
import khademni.utils.MyConnection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Panier;


/**
 *
 * @author mikea
 */
public class Ligne_CommandeService implements ILigne_commande {

     Connection cnx;

    public Ligne_CommandeService() {
        cnx = MyConnection.getInstance().getConnexion();
    }
    @Override
    public void ajouterCommande(Ligne_commande p) {
         String titre_formation="";
              float prix_formation=0;
          try {
             
             
            String sql = "select titre,prix from formation where id_formation="+p.getId_formation();
           java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

              titre_formation=s.getString(1);
              prix_formation=s.getFloat(2);
              System.out.println(titre_formation);
              System.out.println(prix_formation);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
         try {
             
             
            String sql = "insert into ligne_commande(id_panier,id_formation,prix,titre,status)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId_panier());
            ste.setInt(2, p.getId_formation());
             ste.setFloat(3, prix_formation);
             ste.setString(4,titre_formation);
             ste.setInt(5, p.getStatus());
            ste.executeUpdate();
            System.out.println("Commande ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  }

   
    @Override
    public void supprimerCommande(Ligne_commande p) {
        
        
                String sql = "delete from ligne_commande where id_ligne_commande=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId_ligne_commande());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
   }
    
    
      @Override
    public void modifierCommande(Ligne_commande p) {
        
        
                String sql = "update ligne_commande set status="+1+" where id_panier="+1;
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
   }

    @Override
    public ObservableList<Ligne_commande> afficherLigneCommande() {
            
   
        ObservableList<Ligne_commande> commandes = FXCollections.observableArrayList();
        try {
            String sql = "select ligne_commande.id_ligne_commande,panier.id_panier, titre,prix from ligne_commande,panier where ligne_commande.id_panier=panier.id_panier and status="+0+" and panier.id_user="+10;
            java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Ligne_commande u = new Ligne_commande(
                      s.getInt("id_ligne_commande"),
                        s.getInt("id_panier"),
                s.getInt("prix"),
                        s.getString("titre")
                );
                commandes.add(u);

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
    }

    @Override
    public void ViderCommande(Panier p) {
       ObservableList<Ligne_commande> commandes = FXCollections.observableArrayList();
        try {
            String sql = "select ligne_commande.id_ligne_commande,ligne_commande.id_formation,panier.id_panier, titre,prix,status from ligne_commande,panier where ligne_commande.id_panier=panier.id_panier and panier.id_user="+10;
              java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
         
            while (s.next()) {

                Ligne_commande u = new Ligne_commande(
                      s.getInt("id_ligne_commande"),
                        s.getInt("id_panier"),
                        s.getInt("id_formation"),
                s.getInt("prix"),
                        s.getString("titre"),
                        s.getInt("status")
                       
                );
               u.toString();
                modifierCommande(u);

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    
    
        @Override
    public ObservableList<Ligne_commande> afficherMesFormation() {
            
   
        ObservableList<Ligne_commande> commandes = FXCollections.observableArrayList();
        try {
            String sql = "select ligne_commande.id_ligne_commande,panier.id_panier, titre,prix from ligne_commande,panier where ligne_commande.id_panier=panier.id_panier and status="+1+" and panier.id_user="+10;
            java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Ligne_commande u = new Ligne_commande(
                      s.getInt("id_ligne_commande"),
                        s.getInt("id_panier"),
                s.getInt("prix"),
                        s.getString("titre")
                );
                commandes.add(u);

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
    }
    
    
    
    
      
    public ObservableList<Ligne_commande> afficherMesFormationSelonRecherche(String nom) {
            
   
        ObservableList<Ligne_commande> commandes = FXCollections.observableArrayList();
        try {
           String sql = "SELECT ligne_commande.id_ligne_commande, panier.id_panier, titre, prix "
                   + "FROM ligne_commande, panier "
                   + "WHERE ligne_commande.id_panier = panier.id_panier AND status = ? AND panier.id_user = ? AND ligne_commande.titre = ?";
              PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, 0);   // set the value of the first placeholder to 0
        ps.setInt(2, 10);  // set the value of the second placeholder to 10
        ps.setString(3, nom);  // set the value of the third placeholder to the value of nom
        ResultSet s = ps.executeQuery();
            while (s.next()) {

                Ligne_commande u = new Ligne_commande(
                      s.getInt("id_ligne_commande"),
                        s.getInt("id_panier"),
                s.getInt("prix"),
                        s.getString("titre")
                );
                commandes.add(u);

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
    }
    
    
     public ObservableList<Ligne_commande> afficherStatistiqiueFormation() {
    ObservableList<Ligne_commande> commandes = FXCollections.observableArrayList();
         
    try {
        String sql = "SELECT DISTINCT l.id_formation, f.titre, COUNT(*) AS count "
                + "FROM ligne_commande l "
                + "JOIN formation f ON l.id_formation = f.id_formation "
                + "WHERE l.status = 1 "
                + "GROUP BY l.id_formation, f.titre, f.prix "
                + "ORDER BY count DESC "
                + "LIMIT 3";
         
        java.sql.Statement ste = cnx.createStatement();
        ResultSet s = ste.executeQuery(sql);
        while (s.next()) {
            Ligne_commande u = new Ligne_commande(
                s.getInt("id_formation"), 
                 
                s.getInt(
                        "count"),
                s.getString("titre")
            );
            commandes.add(u);
        }           
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return commandes;
}

    
    
  
    }
    
