/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import khademni.entity.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Client;
import khademni.entity.Employeur;
import khademni.entity.Formateur;
import khademni.interfaces.IUtilisateurService;
import khademni.utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class UtilisateurService implements IUtilisateurService {
    
    Connection myconn =MyConnection.getInstance().getConnexion();

 
   
    
    @Override
    public void ajouterEmployeur(Employeur e){
         try {
             
            
              
            String sql = "INSERT INTO user(nom, prenom,login,password,role,mail,domaine,nom_societe)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getPrenom());
            ste.setString(3, e.getLogin());
            ste.setString(4, e.getPassword());
            ste.setString(5, e.getRole());
            ste.setString(6, e.getMail());
            ste.setString(7, e.getDomaine());
            ste.setString(8, e.getNom_societe());
            ste.executeUpdate();
            System.out.println(" Employeur ajouté");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override
    public void ajouterFormateur(Formateur f){
         try {
             
            
              
            String sql = "INSERT INTO user(nom, prenom,login,password,role,mail,domaine,certif)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1, f.getNom());
            ste.setString(2, f.getPrenom());
            ste.setString(3, f.getLogin());
            ste.setString(4, f.getPassword());
            ste.setString(5, f.getRole());
            ste.setString(6, f.getMail());
            ste.setString(7, f.getDomaine());
            ste.setString(8, f.getCertif());
            ste.executeUpdate();
            System.out.println("Formateur ajouté");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    @Override
    public void ajouterClient(Client c){
         try {
           
              
            String sql = "INSERT INTO user(nom, prenom,login,password,role,mail,domaine,solde,cv)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setString(1, c.getNom());
            ste.setString(2, c.getPrenom());
            ste.setString(3, c.getLogin());
            ste.setString(4, c.getPassword());
            ste.setString(5, c.getRole());
            ste.setString(6, c.getMail());
            ste.setString(7, c.getDomaine());
            ste.setFloat(8, c.getSolde());
            ste.setString(9, c.getCv());
            ste.executeUpdate();
            System.out.println("Client ajouté");
            
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public void modifierUtilisateur(Utilisateur u) {
        String sql="update user set nom=?, prenom=?,etat=?,mail=? where id_user=? ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
                ste.setString(1, u.getNom());
                ste.setString(2, u.getPrenom());
                ste.setString(3, u.getEtat());
                ste.setString(4, u.getMail());
                ste.setInt(5, u.getId_user());
    
            ste.executeUpdate();
            System.out.println("Utilisateur modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    
    @Override
    public void supprimerUtilisateur(Utilisateur u) {
        String sql = "delete from user where id_user=?";
        try {
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, u.getId_user());
            ste.executeUpdate();
            System.out.println("Utilisateur supprimé");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public ObservableList<Utilisateur> afficherUtilisateurs() {
        
        ObservableList<Utilisateur> users = FXCollections.observableArrayList();

        try {
            String sql = "select * from user";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Utilisateur u = new Utilisateur(s.getInt(1),
                        s.getString("nom"), s.getString("prenom"),s.getString("role"),s.getString("etat"),s.getString("mail"));
                users.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    
    public void updateImage(String imgPath, int Id) throws SQLException {
    try {
        String sql = "UPDATE user SET image = ? WHERE id_user = ?";
            PreparedStatement ste=myconn.prepareStatement(sql);
        ste.setString(1, imgPath);
        ste.setInt(2, Id);
        ste.executeUpdate();
        System.out.println("image modifié");
    } catch (SQLException ex) {
            System.out.println(ex);
        }
}
    
    
    
}
