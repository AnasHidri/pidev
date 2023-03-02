/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khademni;

import java.sql.Connection;
import khademni.entity.Evenement;
import khademni.entity.Participation;
import khademni.services.EvenementService;
import khademni.services.ParticipationService;
import khademni.utils.MyConnection;
import java.sql.Date;

/**
 *
 * @author mikea
 */
public class Khademni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Connection myconn =MyConnection.getInstance().getConnexion();
        
        EvenementService e=new EvenementService();
        //Evenement ev= new Evenement(1,"hhhh","event","aa","bb");
         Evenement ev= new Evenement(1, Date.valueOf("2223-03-22"), Date.valueOf("2223-04-22"), "ee", "aa", "bb", "cc");
      // e.ajouterEvenement(ev);
       //System.out.println(e.getAll());
         //e.supprimerEvenement(ev);
       // e.modifierEvenement(Date.valueOf("2223-03-22"), Date.valueOf("2223-04-22"),"ll","mm","ddd", ev);
      // e.modifierEvenement(Date.valueOf("2000-04-04"), Date.valueOf("2111-11-03"), "mmm", "ppp", "a", ev);
       //System.out.println( e.MaListe());
       //System.out.println(e.Stat());
       System.out.println(e.getMostLikedEvents());
        
        
        ParticipationService p=new ParticipationService();
        Participation participation= new Participation(37,1,"bbb");
        //p.ajouterParticipation(participation);
       // p.supprimerParticipation(participation);
       // p.modifierParticipation("achevée", participation);
      // p.likeEvent(participation);
     // p.DislikeEvent(participation);
    //p.getLikesAndDislikesCount(participation);
     //System.out.println(p.getLikesAndDislikesCount(participation));
       //System.out.println(p.getAll());
       //System.out.println(p.getLikesAndDislikesCount(participation));
      // int[] counts = p.getLikesAndDislikesCount(participation);
       //System.out.println("Likes: " + counts[0]);
     // System.out.println("Dislikes: " + counts[1]);
    // System.out.println(p.Participants(33));
    
    }
    
}
