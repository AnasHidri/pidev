/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.services;

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
import khademni.entity.Note;
import khademni.entity.Question;
import khademni.utils.MyConnection;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class NoteService {
    
            private final Connection cnx;

    private static NoteService instance;
    
        public NoteService() {
        cnx = MyConnection.getInstance().getConnexion();
    }
    
    public static NoteService getInstance()
    {
        if (instance == null) {
            instance = new NoteService();
        }
        return instance; 
    }
    
        
   public void addNote(Note q)throws SQLDataException, SQLException{
        
         
         
         String query ="INSERT INTO `note`(`id_question`,`id_user`,`note`) VALUES (?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setInt(1,q.getId_question());
                st.setInt(2, q.getId_user());
                st.setFloat(3, q.getNote()) ;
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }
   
   
   
       public ObservableList<Note> getNoteByUser(int id) throws SQLDataException {

        
        List<Note> list =new ArrayList<Note>();
        int count =0;
        
        String requete="select * from note where id_user="+id;
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Note e = new Note();
                e.setId_note(rs.getInt("id_note"));
                e.setId_question(rs.getInt("id_question"));
                e.setId_user(rs.getInt("id_user"));
                e.setNote(rs.getInt("note"));
                
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
       
       
       
          
       public ObservableList<Note> getNoteAll() throws SQLDataException {

        
        List<Note> list =new ArrayList<Note>();
        int count =0;
        
        String requete="select * from note ";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Note e = new Note();
                e.setId_note(rs.getInt("id_note"));
                e.setId_question(rs.getInt("id_question"));
                e.setId_user(rs.getInt("id_user"));
                e.setNote(rs.getInt("note"));
                
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
}
