/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.services;

import khademni.entity.Question;
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
public class QuestionService {
    
        
    private final Connection cnx;

    private static QuestionService instance;
    
        public QuestionService() {
        cnx = MyConnection.getInstance().getConnexion();
    }
    
    public static QuestionService getInstance()
    {
        if (instance == null) {
            instance = new QuestionService();
        }
        return instance; 
    }
    
   public void addQuestion(Question q)throws SQLDataException, SQLException{
        
         
         
         String query ="INSERT INTO `question`(`question`,`type_reponse`,`idquiz`) VALUES (?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getQuestion());
                st.setString(2, q.getType_question());
                st.setInt(3, q.getId_quiz()) ;
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierQuestion(Question e) throws SQLDataException {

               
                String query = "UPDATE `question` SET `question`=?,`type_reponse`=? WHERE `id_question` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getQuestion());
                st.setString(2,e.getType_question());
                st.setInt(3, e.getId_question());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Question> getAllQuestion(int id) throws SQLDataException {

        
        List<Question> list =new ArrayList<Question>();
        int count =0;
        
        String requete="select * from question where idquiz="+id;
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Question e = new Question();
                e.setId_question(rs.getInt("id_question"));
                e.setQuestion(rs.getString("question"));
                e.setType_question(rs.getString("type_reponse"));
                e.setId_quiz(rs.getInt("idquiz"));
                
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
       public Question get_QuestionById(int i) {
        Question e = new Question();
        int nombre = 0;
        String requete = "select * from question where id_question="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId_question(rs.getInt("id_question"));
                e.setQuestion(rs.getString("question"));
                e.setType_question(rs.getString("type_reponse"));
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
       
    
    
    

    public boolean deleteQuestion(int idCat) throws SQLDataException {

        
        
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM question WHERE id_question="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    
    
        public int NumQuestionParQuiz (int p) throws SQLException{
      
        int c =0 ;
                    String query = "SELECT COUNT(*) FROM question WHERE idquiz="+p;
                    Statement st  = cnx.createStatement();
                    ResultSet rs = st.executeQuery(query);
                     while(rs.next()){
                         
                         
                   c=c+ 1;
                     
                     }
                    // System.out.println("9adechh"+c);
                     return c ;


 }
    
    
    
    
}
