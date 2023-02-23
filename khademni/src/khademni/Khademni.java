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
        Evenement ev= new Evenement(1,"hhhh","event","aa","bb");
//        e.ajouterEvenement(ev);
         //e.supprimerEvenement(ev);
        //e.modifierEvenement("recru","journee","marsa", ev);
         System.out.println( e.MaListe());
        
        
        ParticipationService p=new ParticipationService();
        Participation participation= new Participation(18,2,"bbb");
         //p.ajouterParticipation(participation);
       // p.supprimerParticipation(participation);
       // p.modifierParticipation("achevée", participation);
      //p.likeEvent(participation);
     // p.DislikeEvent(participation);
    p.getLikesAndDislikesCount(participation);
     System.out.println(p.getLikesAndDislikesCount(participation));
       //System.out.println(p.getAll());
    }
    
}
