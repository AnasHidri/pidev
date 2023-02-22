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
            ste.setString(6, O.getDate_debut());
            ste.setString(7, O.getDate_limite());
            ste.executeUpdate();
            System.out.println(" Offre ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    @Override
    public void modifierOffre(Offre O) {
 String sql="update offre set  titre=?, description=?,adresse_societe=?,domaine_offre=?,date_debut=?,date_limite=? where id_user=?  ";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
             
                ste.setString(1,O.getTitre());
                ste.setString(2, O.getDescription());
                ste.setString(3, O.getAdresse_societe());
                ste.setString(4, O.getDomaine_offre());
                ste.setString(5, O.getDate_debut());
                ste.setString(6, O.getDate_limite());
                 ste.setInt(7, O.getId_user());
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
            String sql = "select * from offre";
            Statement ste = myconn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Offre o = new Offre(s.getInt(1),
                       s.getInt(2),
                       s.getString("titre"),
                       s.getString("description"),
                       s.getString("adresse_societe"),
                       s.getString("domaine_offre"),
                       s.getString("date_debut"),
                        
                       s.getString("date_limite"));
              
                       OffreList.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return OffreList;
    }




    }

  


