/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.services;

import khademni.entity.Quiz;
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
public class QuizService {
    
    
    
        private final Connection cnx;

    private static QuizService instance;
    
        public QuizService() {
        cnx = MyConnection.getInstance().getConnexion();
    }
    
    public static QuizService getInstance()
    {
        if (instance == null) {
            instance = new QuizService();
        }
        return instance; 
    }
    
   public void addQuiz(Quiz q)throws SQLDataException, SQLException{
        
         
         
         String query ="INSERT INTO `quiz`(`nom`) VALUES (?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getNom());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(QuizService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierQuiz(Quiz e) throws SQLDataException {

               
                String query = "UPDATE `quiz` SET `nom`=? WHERE `id_quiz` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getNom());
                st.setInt(2,e.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(QuizService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Quiz> getAllQuiz() throws SQLDataException {

        
        List<Quiz> list =new ArrayList<Quiz>();
        int count =0;
        
        String requete="select * from quiz";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Quiz e = new Quiz();
                e.setId(rs.getInt("id_quiz"));
                e.setNom(rs.getString("nom"));
                
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
            Logger.getLogger(QuizService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Quiz get_QuizById(int i) {
        Quiz e = new Quiz();
        int nombre = 0;
        String requete = "select * from quiz where id_quiz="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId(rs.getInt("id_quiz"));
                e.setNom(rs.getString("nom"));
                
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
       
    
    
    

    public boolean deleteQuiz(int idCat) throws SQLDataException {

        
        
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM quiz WHERE id_quiz="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuizService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    
}
