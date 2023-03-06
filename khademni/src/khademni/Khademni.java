/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khademni;

import khademni.entity.Formation;
import khademni.services.FormationService;

/**
 *
 * @author mikea
 */
public class Khademni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormationService fs=new FormationService();
        Formation f1=new Formation(1,"azouur", "zerzer", 15, "sfd");
        
        
       // fs.ajouterFormation(f1);
        
          //fs.supprimerFormation(f1);
        
        //fs.modifierFormation(f1);
        
        
        //System.out.println(fs.afficherFormation());
        
       // System.out.println("byForm :: "+fs.getFormationsByFormateur(1));
        
    }
    
}
