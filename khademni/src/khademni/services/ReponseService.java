/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.services;

import khademni.entity.Question;
import khademni.entity.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import khademni.utils.MyConnection;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class ReponseService {
    
       
    private final Connection cnx;

    private static ReponseService instance;
    
        public ReponseService() {
        cnx = MyConnection.getInstance().getConnexion();
    }
    
    public static ReponseService getInstance()
    {
        if (instance == null) {
            instance = new ReponseService();
        }
        return instance; 
    }
    
   public void addReponse(Reponse q)throws SQLDataException, SQLException{
        
         
         
         String query ="INSERT INTO `reponse`(`reponse`,`type_reponse`,`id_question`) VALUES (?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getReponse());
                st.setString(2, q.getType());
                st.setInt(3, q.getId_question()) ;
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierReponse(Reponse e) throws SQLDataException {

               
       String query = "UPDATE `reponse` SET `reponse`=?,`type_reponse`=? WHERE `id_reponse` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getReponse());
                st.setString(2,e.getType());
                st.setInt(3, e.getId_reponse());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Reponse> getAllReponse(int id) throws SQLDataException {

        
        List<Reponse> list =new ArrayList<Reponse>();
        int count =0;
        
        String requete="select * from reponse where id_question="+id;
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Reponse e = new Reponse();
                e.setId_reponse(rs.getInt("id_reponse"));
                e.setReponse(rs.getString("reponse"));
                e.setType(rs.getString("type_reponse"));
                e.setId_question(rs.getInt("id_question"));
                
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Reponse get_ReponseById(int i) {
        Reponse e = new Reponse();
        int nombre = 0;
        String requete = "select * from reponse where id_reponse="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId_reponse(rs.getInt("id_reponse"));
                e.setReponse(rs.getString("reponse"));
                e.setType(rs.getString("type_reponse"));
                e.setId_question(rs.getInt("id_question"));
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
       
    
    
    

    public boolean deleteReponse(int idCat) throws SQLDataException {

        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM reponse WHERE id_reponse="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    
    
}
