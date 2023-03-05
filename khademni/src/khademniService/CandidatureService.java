/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademniService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Candidature;
import khademni.interfaces.ICandidature;
import khademni.utils.MyConnection;

/**
 *
 * @author CYBERLAND
 */
public class CandidatureService implements ICandidature <Candidature>{
       Connection myconn =MyConnection.getInstance().getmyconn();

    @Override
    public void ajouterCandidature(Candidature P) {
 try {
     
              
            String sql = "INSERT INTO candidature(id_candidature,id_offre,id_user,etat)"
                    + "VALUES (?,?,?,?)";
            
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1, P.getId_candidature());
            ste.setInt(2, P.getId_offre());
            ste.setInt(3, P.getId_user());
             ste.setString(4, P.getEtat());
            
            ste.executeUpdate();
            System.out.println(" Candidature ajouté");
            
        } catch (SQLException ex){ 
            System.out.println(ex.getMessage());
        }    }

  
    public void RefuserCandidature(Candidature P,int id) {
String sql="update  user,candidature,offre set candidature.etat='Refusée'  where user.id_user = Offre.id_user and offre.id_offre = candidature.id_offre and offre.id_offre = "+id;
        try {
          PreparedStatement ps = myconn.prepareStatement(sql);

            ps.executeUpdate(sql);
                          
            System.out.println("Candidature Refusé");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }
     public void AccepterCandidature(Candidature P,int id) {
 String sql="update user,candidature,offre set candidature.etat='Accepté' where user.id_user = Offre.id_user and offre.id_offre = candidature.id_offre and offre.id_offre = "+id;
        try {
               PreparedStatement ps = myconn.prepareStatement(sql);

            ps.executeUpdate(sql);
            System.out.println("Candidature Accepté");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       }
       
 public ObservableList<Candidature> afficherCandidatureEmployeur1(int id) {
       ObservableList<Candidature> CandidatureList1 = FXCollections.observableArrayList();
        try {
           String sql = "select offre.titre, offre.id_offre from user,candidature,offre where user.id_user=Offre.id_user and offre.id_offre=candidature.id_offre and offre.id_offre = "+id;
           
  Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
        
           Candidature ce= new Candidature(s.getString("titre"),s.getInt(2));
            CandidatureList1.add (ce);
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CandidatureList1;
    }
     
   public ObservableList<Candidature> afficherCandidatureEmployeur2(int id) {
    ObservableList<Candidature> CandidatureList2 = FXCollections.observableArrayList();
    try {
        String sql = "SELECT user.nom, user.prenom, user.email FROM user, candidature, offre WHERE user.id_user = offre.id_user AND offre.id_offre = candidature.id_offre AND offre.id_offre = "+id;
                   
  Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
   
        while (s.next()) {
            Candidature Candidat = new Candidature(
                    s.getString("nom"),
                    s.getString("prenom"),
                    s.getString("email"));
            CandidatureList2.add(Candidat);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return CandidatureList2;
}

public ObservableList<Candidature> afficherCandidatureEmployeur3(int id) {
    ObservableList<Candidature> CandidatureList3 = FXCollections.observableArrayList();
    try {
        String sql = "SELECT candidature.etat FROM user, candidature, offre WHERE user.id_user = candidature.id_user AND offre.id_offre = candidature.id_offre AND offre.id_offre = "+id;
                  
  Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
      
        while (s.next()) {
            Candidature ce = new Candidature(s.getString("etat"));
            CandidatureList3.add(ce);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return CandidatureList3;
}

      
      
      
     public ObservableList<Candidature> afficherCandidatureClient1() {
       ObservableList<Candidature> CandidatureClientList = FXCollections.observableArrayList();
        try {
           String sql = "select offre.titre,offre.description,offre.adresse_societe,offre.domaine_offre,offre.date_debut,offre.date_limite from  offre,candidature where  offre.id_offre=candidature.id_offre and candidature.id_user="+2;
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
           
          Candidature c = new Candidature(s.getString("titre"),
            s.getString("description"),
                  s.getString("adresse_societe"),
            s.getString("domaine_offre"),
        s.getDate(5),
        s.getDate(6));
       
      CandidatureClientList.add(c);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CandidatureClientList;
    }
     public ObservableList<Candidature> afficherCandidatureClient2() {
       ObservableList<Candidature> CandidatureEtat = FXCollections.observableArrayList();
        try {
           String sql = "select candidature.etat from offre,candidature where  offre.id_offre=candidature.id_offre and candidature.id_user="+2;
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Candidature c = new Candidature(s.getString("etat"));
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

 

   

    
}