/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;
import khademni.interfaces.IPanier;
import khademni.utils.MyConnection;

/**
 *
 * @author mikea
 */
public class PanierService implements IPanier {
    
    
    Connection cnx;

    public PanierService() {
        cnx = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void ajouterPanier(Panier p) {
    
           try {
            String sql = "insert into panier(id_user,somme)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId_user());
            ste.setFloat(2, p.getSomme());
            ste.executeUpdate();
            System.out.println("Panier ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifierPanier(Panier p) {
       
       int prixtotal=0;
        try {
            String sql = "select prix from ligne_commande,panier where ligne_commande.id_panier= panier.id_panier"
                    ;
            java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                prixtotal = prixtotal + s.getInt("prix");
              
                System.out.println(prixtotal);
            }
              String sql2="update panier set somme= "+prixtotal;
            
          PreparedStatement ste2 = cnx.prepareStatement(sql2);
            ste2.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerPanier(Panier p) {
            String sql = "delete from panier where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId_panier());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

  
    @Override
    public ObservableList<String> afficherPanier() {
            
   
        ObservableList<String> l_formation = FXCollections.observableArrayList();
        try {
            String sql = "select formation.titre,formation.prix from formation,panier,ligne_commande where ligne_commande.id_panier="+1+" and"
                    + " panier.id_panier="+1+" and ligne_commande.id_formation=formation.id_formation";
            java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                String u =s.getString("titre");
                int p= s.getInt("prix");
                
                l_formation.add(u);
                l_formation.add(p+"");
             
             

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      return l_formation;
    }

    @Override
    public int affichesomme(Panier p) {
        int prixtot=0;
        String sql3 = "select somme from panier,ligne_commande where panier.id_panier =ligne_commande.id_panier and status="+0+" and panier.id_user="+10;
   try {
            
              java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql3);
           

              while (s.next()) {

                prixtot=prixtot+s.getInt("somme");
             System.out.println(prixtot);

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return prixtot;
    }

    @Override
    public void payer(Panier p) {
      
        
        int soldeuser =0;
        
        
       String sql3 = "select solde from user";
   try {
            
              java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql3);
           
             
            
              while (s.next()) {

                soldeuser=soldeuser+s.getInt("solde");
             System.out.println("solde user" + soldeuser);
             
             

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        int prixtot =  affichesomme(p);
        int nouveausolde = soldeuser-prixtot;
         try {
            String sql = "update user,panier,ligne_commande set user.solde="+nouveausolde;
                    
             PreparedStatement ste2 = cnx.prepareStatement(sql);
            ste2.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
  
}
