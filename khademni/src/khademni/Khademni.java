/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khademni;

import java.sql.Connection;
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;
import khademni.services.Ligne_CommandeService;
import khademni.services.PanierService;
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
        
        PanierService p = new PanierService();
        Panier panier = new Panier(10,100);
        /*p.ajouterPanier(panier);*/
      // p.modifierPanier(panier);
       Ligne_CommandeService cm= new Ligne_CommandeService();
       Ligne_commande commande= new Ligne_commande(1,1,500,"SQL",0);
        //cm.ajouterCommande(commande);
        System.out.println(cm.afficherLigneCommande());
       
        
        p.affichesomme(panier);
    }
    
}
