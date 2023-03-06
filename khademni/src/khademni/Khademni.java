/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khademni;

import java.sql.Connection;
import khademni.entity.Formation;
import khademni.services.FormationService;
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
        Connection myconn =MyConnection.getInstance().getConnexion();
        FormationService fs=new FormationService();
        Formation f1=new Formation("aa", "bb", "cc", (float) 12.5);
        fs.ajouterFormation(f1);
      //fs.supprimerFormation(f1);
      //fs.modifierFormation("bb", f1);
      //fs.afficherFormation();
      //System.out.println(fs.getAll());

    }
    
}
