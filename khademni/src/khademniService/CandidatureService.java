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

    @Override
    public void modifierCandidature(Candidature P) {
String sql="update candidature set etat=? where id_offre=? ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
                ste.setString(1,P.getEtat());
                ste.setInt(2,P.getId_offre());
            ste.executeUpdate();
            System.out.println("Candidature modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }

    public ObservableList<String> afficherCandidatureEmployeur() {
       ObservableList<String> CandidatureList = FXCollections.observableArrayList();
        try {
           String sql = "select  offre.titre,user.nom,user.prenom,user.adresse ,candidature.etat from user,candidature,offre where user.id_user=candidature.id_user and offre.id_offre=candidature.id_offre and offre.id_offre="+70;
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
             /*   Candidature c = new Candidature(s.getInt(1), 
                        s.getInt(2),s.getInt(3), s.getString("etat"));*/
             //   CandidatureList.add(c);
            CandidatureList.add(s.getString(1));
            CandidatureList.add(s.getString(2));
            CandidatureList.add(s.getString(3));
            CandidatureList.add(s.getString(4));
            CandidatureList.add(s.getString(5));
}

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CandidatureList;
    }
    
     public ObservableList<String> afficherCandidatureClient() {
       ObservableList<String> CandidatureClientList = FXCollections.observableArrayList();
        try {
           String sql = "select offre.id_offre offre.titre,offre.description,offre.domaine_offre,offre.adresse_societe,offre.date_debut,offre.date_limite,candidature.etat from  offre,candidature where  offre.id_offre=candidature.id_offre and candidature.id_user="+2;
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
             /*   Candidature c = new Candidature(s.getInt(1), 
                        s.getInt(2),s.getInt(3), s.getString("etat"));*/
             //   CandidatureList.add(c);
            CandidatureClientList.add(s.getString(1));
            CandidatureClientList.add(s.getString(2));
            CandidatureClientList.add(s.getString(3));
            CandidatureClientList.add(s.getString(4));
            CandidatureClientList.add(s.getDate(5).toString());
            CandidatureClientList.add(s.getDate(6).toString());
            CandidatureClientList.add(s.getString(7));

            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CandidatureClientList;
    }
    @Override
    public void supprimerCandidature(Candidature P) {
  String sql = "delete from candidature where id_offre=? ";
        try {
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1,P.getId_offre());
             ste.executeUpdate();
            System.out.println("Offre supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }




   
    
}
