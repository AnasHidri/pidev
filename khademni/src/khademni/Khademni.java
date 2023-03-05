/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khademni;

import khademni.entity.Utilisateur;
import java.sql.Date;
import khademni.entity.Client;
import khademni.entity.Employeur;
import khademni.entity.Formateur;
import services.UtilisateurService;

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
        
        
        UtilisateurService us = new UtilisateurService();
        Employeur e2 = new Employeur("anas", "mahfoudh", "yass11", "1234", "Employeur", "yas@gm.com", "BI", "ST2I");
        Formateur f1 = new Formateur("yassine", "mahfoudh", "yass11", "1234", "FORMATEUR", "yas@gm.com", "BI", "certif JAVA");
        Client c1 = new Client("yassine", "mahfoudh", "yass11", "1234", "CLIENT", "yas@gm.com", "BI", 1000,"cv FRANCAIS");
        
        System.out.println("us.countUsers();"+us.countUsers());
        
       //us.ajouterEmployeur(e2);
       //us.ajouterClient(c1);

      // us.ajouterUtilisateur(u2);

     
      // us.modifierUtilisateur(u2);
       
      // us.supprimerUtilisateur(u1);
       
      //  System.out.println(us.afficherUtilisateurs());

      
      /*
      Utilisateur uu=us.findUserByLogin("anas12","12345678");
      Client emp = (Client)uu;
                        Utilisateur.setCurrent_User(emp);

        System.out.println("findByloginddddd :: "+emp.getCv());
                System.out.println("Curent  :: "+Utilisateur.getCurrent_User());

    */
    }
    
}
