
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
    
    
    Offre O =new Offre(1,"css","css","tunis","html","22/2/2023","22/3/2023");
     OffreService os= new OffreService();
    // os.ajouterOffre(O);
    // os.modifierOffre(O);
     
     CandidatureService cs = new CandidatureService();
    System.out.println(cs.afficherCandidature()); 
    }
    
}
