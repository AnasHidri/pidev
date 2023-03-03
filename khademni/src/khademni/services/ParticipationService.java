/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.services;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import khademni.entity.Evenement;
import khademni.entity.Participation;
import khademni.entity.Utilisateur;
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
             if (participantExists(p.getId_user(),p.getId_evenement())) {
             System.out.println("L'utilisateur a deja participé a cet evenement!!");
            
        }
             else{
            String sql = "insert into participation(id_evenement,id_user,status)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId_evenement());
            ste.setInt(2, p.getId_user());
            ste.setString(3, p.getStatus());
            ste.executeUpdate();
            System.out.println("Participation ajoutée");
             }
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
    public ObservableList<Participation> getAll() {
         ObservableList<Participation> participations = FXCollections.observableArrayList();
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
    
    public boolean participantExists(int id_user, int id_evenement) throws SQLException {
        String query = "SELECT * FROM participation WHERE id_user = ? and id_evenement =?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, id_user);
        statement.setInt(2, id_evenement);
        ResultSet result = statement.executeQuery();
        return result.next();
    }
    
    public void likeEvent(Participation p) {
        try {
            if (participantExists(p.getId_user(),p.getId_evenement())){
                 
                 String sql = "update participation set vote="+1+" where id_evenement=? and id_user=?";
        
            PreparedStatement ste = cnx.prepareStatement(sql);
           // ste.setInt(1, p.getVote());
            ste.setInt(1,p.getId_evenement());
            ste.setInt(2,p.getId_user());
            ste.executeUpdate();
            System.out.println("like ajouté");
        } 
        
             else{
                 System.out.println("Vous n'avez pas encore participé");
             }
        }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void DislikeEvent(Participation p) {
        try {
            if (participantExists(p.getId_user(),p.getId_evenement())){
                 
                 String sql = "update participation set vote="+2+" where id_evenement=? and id_user=?";
        
            PreparedStatement ste = cnx.prepareStatement(sql);
           // ste.setInt(1, p.getVote());
            ste.setInt(1,p.getId_evenement());
            ste.setInt(2,p.getId_user());
            ste.executeUpdate();
            System.out.println("dislike ajouté");
        } 
        
             else{
                 System.out.println("Vous n'avez pas encore participé");
             }
        }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int[] getLikesAndDislikesCount(Participation p) {
    int[] counts = new int[2];
    try {
        // Préparer la requête SQL pour sélectionner tous les votes pour l'événement donné
        String sql = "SELECT vote FROM participation WHERE id_evenement = ?";
        PreparedStatement stmt = cnx.prepareStatement(sql);
        stmt.setInt(1, p.getId_evenement());
        
        // Exécuter la requête SQL et parcourir les résultats pour compter les votes positifs et négatifs
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int vote = rs.getInt("vote");
            if (vote == 1) {
                counts[0]++;
            } else if (vote == 2) {
                counts[1]++;
            }
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors du calcul du nombre de likes et dislikes : " + ex.getMessage());
    }
    return counts;
}
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(listener);
}
    
    private int likesCount = 0;
    private int dislikesCount = 0;
    
    public void updateLikesAndDislikes(Participation p) {
        int[] newLikesAndDislikes = getLikesAndDislikesCount(p);
        int[] oldLikesAndDislikes = { likesCount, dislikesCount };
        likesCount = newLikesAndDislikes[0];
        dislikesCount = newLikesAndDislikes[1];
        pcs.firePropertyChange("likesAndDislikes", oldLikesAndDislikes, newLikesAndDislikes);
    }
    
    
    
    public ObservableList<Utilisateur> Participants(int id) {
         ObservableList<Utilisateur> participations = FXCollections.observableArrayList();
        try {
            String sql = "select nom,prenom,mail from user,participation,evenement where evenement.id_evenement=participation.id_evenement and participation.id_user=user.id_user and evenement.id_evenement="+id;
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

             Utilisateur u = new Utilisateur(s.getString("nom"),s.getString("prenom"),s.getString("mail"));
               participations.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return participations;
    }
    
    

}