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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Candidature;
import khademni.entity.Offre;
import khademni.entity.Utilisateur;
import khademni.interfaces.ICandidature;
import khademni.utils.MyConnection;

/**
 *
 * @author CYBERLAND
 */
public class CandidatureService implements ICandidature <Candidature>{
       Connection myconn =MyConnection.getInstance().getConnexion();

    @Override
    public void ajouterCandidature(Candidature P) {
 try {
     
                        Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
            String sql = "INSERT INTO candidature(id_candidature,id_offre,id_user,etat)"
                    + "VALUES (?,?,?,?)";
            
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, P.getId_candidature());
            ste.setInt(2, P.getId_offre());
            ste.setInt(3, utilisateur.Current_User.getId_user());
             ste.setString(4, P.getEtat());
            
            ste.executeUpdate();
            System.out.println(" Candidature ajouté");
            
        } catch (SQLException ex){ 
            System.out.println(ex.getMessage());
        }    }

  
    public void RefuserCandidature(Candidature P,int id) {
           Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
String sql="update  user,candidature,offre set candidature.etat='Refusée'  where user.id_user = candidature.id_user and offre.id_offre = candidature.id_offre and user.id_user = "+utilisateur.Current_User.getId_user();
        try {
          PreparedStatement ps = myconn.prepareStatement(sql);

            ps.executeUpdate(sql);
            System.out.println("Candidature Refusé");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }
     public void AccepterCandidature(Candidature P,int id) {
                    Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
 String sql="update user,candidature,offre set candidature.etat='Accepté'   where user.id_user = candidature.id_user and offre.id_offre = candidature.id_offre and  user.id_user = "+utilisateur.Current_User.getId_user();
        try {
               PreparedStatement ps = myconn.prepareStatement(sql);

            ps.executeUpdate(sql);
            System.out.println("Candidature Accepté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       }
       
 public ObservableList<Offre> afficherOffreEmployeur1(int id) {
       ObservableList<Offre> OffreList1 = FXCollections.observableArrayList();
        try {
           String sql = "select offre.titre, offre.id_offre from user,candidature,offre where user.id_user=Offre.id_user and offre.id_offre=candidature.id_offre and offre.id_offre = "+id;
           
  Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
        
           Offre O= new Offre(s.getInt(2),s.getString("titre"));
            OffreList1.add (O);
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return OffreList1;
    }
     
   public ObservableList<Utilisateur> afficherUserEmployeur2(int id) {
    ObservableList<Utilisateur> UserList2 = FXCollections.observableArrayList();
    try {
        String sql = "SELECT  user.id_user,user.nom, user.prenom, user.mail FROM user, candidature, offre WHERE user.id_user = candidature.id_user AND offre.id_offre = candidature.id_offre AND offre.id_offre = "+id;
                   
  Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
   
        while (s.next()) {
            Utilisateur U = new Utilisateur(
                    s.getInt("id_user"),
                    s.getString("nom"),
                    s.getString("prenom"),
                    s.getString("mail"));
            UserList2.add(U);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return UserList2;
}

public ObservableList<Candidature> afficherCandidatureEmployeur3(int id) {
    ObservableList<Candidature> CandidatureList3 = FXCollections.observableArrayList();
    try {
        String sql = "SELECT candidature.etat ,candidature.id_offre FROM user, candidature, offre WHERE user.id_user = offre.id_user AND offre.id_offre = candidature.id_offre AND offre.id_offre = "+id;
                  
  Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
      
        while (s.next()) {
            Candidature ce = new Candidature(s.getInt("id_offre"),s.getString("etat"));
            System.out.println("canddd::"+ce);
            CandidatureList3.add(ce);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return CandidatureList3;
}

      
      
      
     public ObservableList<Offre> afficherOffreClient1() {
                   Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
       ObservableList<Offre> CandidatureOffreList = FXCollections.observableArrayList();
        try {
           String sql = "select offre.titre,offre.description,offre.adresse_societe,offre.domaine_offre,offre.date_debut,offre.date_limite from  offre,candidature where  offre.id_offre=candidature.id_offre and candidature.id_user="+utilisateur.Current_User.getId_user();
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
           
          Offre O = new Offre(s.getString("titre"),
            s.getString("description"),
                  s.getString("adresse_societe"),
            s.getString("domaine_offre"),
        s.getDate(5));
  
       
      CandidatureOffreList.add(O);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CandidatureOffreList;
    }
     public ObservableList<Candidature> afficherCandidatureClient2( ) {
                            Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
       ObservableList<Candidature> CandidatureEtat = FXCollections.observableArrayList();
        try {
           String sql = "select candidature.etat ,Candidature.id_offre from offre,candidature where  offre.id_offre=candidature.id_offre and candidature.id_user="+utilisateur.Current_User.getId_user();
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Candidature c = new Candidature(s.getInt("id_offre"),s.getString("etat"));
              CandidatureEtat.add(c);
            

            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CandidatureEtat;
    }
    @Override
    public void supprimerCandidature(Candidature P) {
  String sql = "delete from candidature where id_offre=? ";
        try {
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1,P.getId_offre());
             ste.executeUpdate();
            System.out.println("Candidature supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

   public void AnnulerCandidature(Candidature P) {
  String sql = "delete from candidature where id_offre=? ";
        try {
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1,P.getId_offre());
             ste.executeUpdate();
            System.out.println("Candidature supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
    
  public void AffichRefuserCandidature(Candidature P, int id) {
String sql="update candidature set etat='Refuser' where user.id_user=candidature.id_user and offre.id_offre=candidature.id_offre and offre.id_offre= "+id;
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
                ste.setString(1,P.getEtat());
                ste.setInt(2,P.getId_offre());
            ste.executeUpdate();
            System.out.println("Candidature Refusé");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }
    
  public void AffichAttenteCandidature(Candidature P,int id) {
String sql="update candidature set etat='Accepter' where user.id_user=candidature.id_user and offre.id_offre=candidature.id_offre and candidature.etat='En attente'and offre.id_offre= "+id;
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
                ste.setString(1,P.getEtat());
                ste.setInt(2,P.getId_offre());
            ste.executeUpdate();
            System.out.println("Candidature En Attente");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }

 

 
public String  findCVById(int id_user) {
      String ch="";
    try {
       Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
    String sql = "SELECT cv FROM user, candidature, offre WHERE user.id_user = candidature.id_user AND offre.id_offre = candidature.id_offre AND user.id_user = " + id_user;
    Statement ste = myconn.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    if (rs.next()) {
       ch=rs.getString("cv");
       
    } else {
        System.out.println("No user found with ID: " + id_user);
    }
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
           
return ch;

}  

    
}