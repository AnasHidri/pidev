
import khademni.entity.Candidature;
import khademni.entity.Offre;
import khademniService.CandidatureService;
import khademniService.OffreService;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CYBERLAND
 */
public class Khademni {
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    
   // Offre O =new Offre(1,"css","css","tunis","html","22/2/2023","22/3/2023");
     OffreService os= new OffreService();
    // os.ajouterOffre(O);
    // os.modifierOffre(O);
         CandidatureService Cs = new CandidatureService();
      Candidature C =new Candidature(68,1,"en attente ");
          // Cs.ajouterCandidature(C);
    System.out.println(Cs.afficherCandidatureEmployeur1());
       System.out.println(Cs.afficherCandidatureEmployeur2());

       System.out.println(Cs.afficherCandidatureEmployeur3());
       //Cs.RefuserCandidature(C);
    }
    
}
