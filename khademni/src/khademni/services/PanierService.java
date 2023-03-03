/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.services;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.entity.Historique;
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;
import khademni.entity.Utilisateur;
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
    public void ajouterPanier(int u) {
        boolean verif = false;
       try {
        Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
        String sql2 = "select panier.id_panier from panier, user where panier.id_user=user.id_user and user.id_user="+u;
    
          java.sql.Statement ste2 = cnx.createStatement();
            ResultSet s = ste2.executeQuery(sql2);
            while (s.next()) {
                    verif = true;
               
            }
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
       if (verif==false){
           try {
                 Utilisateur utilisateur = new Utilisateur();
            String sql = "insert into panier(id_user,somme)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, u);
            ste.setFloat(2, 0);
            ste.executeUpdate();
            System.out.println("Panier ajoutée");
            
            HistoriqueService hs= new HistoriqueService();
            String ajoutaction="Ajout panier";
             LocalDate today = LocalDate.now();
            Historique h= new Historique(u,java.sql.Date.valueOf(today),ajoutaction);
            hs.ajouterHistorique(h);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }
    }

    @Override
    public void modifierPanier(Panier p) {
       
       int prixtotal=0;
        try {
               Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
            String sql = "select prix from ligne_commande,panier where ligne_commande.id_panier= panier.id_panier and status="+0+" and panier.id_user="+utilisateur.Current_User.getId_user()
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
            String sql = "delete from panier where id_panier=?";
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
              Utilisateur utilisateur = new Utilisateur();
            String sql = "select formation.titre,formation.prix from formation,panier,ligne_commande where formation.id_formation=ligne_commande.id_formation and panier.id_panier=ligne_commande.id_panier and panier.id_user="+utilisateur.Current_User.getId_user();
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
         Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
        String sql3 = "select somme from panier where panier.id_user="+utilisateur.Current_User.getId_user();
   try {
            
              java.sql.Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql3);
           

              while (s.next()) {

                prixtot=prixtot+s.getInt("somme");
             System.out.println("hedhi somme" +prixtot);

            }
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return prixtot;
    }

    @Override
   public void payer(Panier p) {
    int soldeuser = 0;
             Utilisateur utilisateur = new Utilisateur();
           System.out.println("current user id anaaas::"+utilisateur.Current_User.getId_user());
    String sql3 = "select solde from user where id_user="+utilisateur.Current_User.getId_user();
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
  
    int prixtot = affichesomme(p);
    int nouveausolde = soldeuser - prixtot;

    try (PreparedStatement ste2 = cnx.prepareStatement("update user set user.solde=? where id_user="+utilisateur.Current_User.getId_user())) {
        ste2.setInt(1, nouveausolde);
        ste2.executeUpdate();
        System.out.println("User balance updated successfully.");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    // send email notification
    final String username = "anashidri36@gmail.com";
    final String password = "yadnnyyowlxctwcr";
    String recipientEmail = "anashidri36@gmail.com";
    String subject = "Payment notification";
    String message = "The payment process has completed successfully.";
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
    try {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        msg.setSubject(subject);
        msg.setText(message);
        Transport.send(msg);
        System.out.println("Email notification sent successfully.");
    } catch (MessagingException ex) {
        System.out.println(ex.getMessage());
    }
    
    
     HistoriqueService hs= new HistoriqueService();
            String ajoutaction="Paiement effectus avec success";
             LocalDate today = LocalDate.now();
            Historique h= new Historique(utilisateur.Current_User.getId_user(),java.sql.Date.valueOf(today),ajoutaction);
            hs.ajouterHistorique(h);
            
}

  
  
}
