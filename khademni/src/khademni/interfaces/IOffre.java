/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import javafx.collections.ObservableList;
import khademni.entity.Offre;



/**
 *
 * @author CYBERLAND
 */
public interface IOffre {
    
  
    public void ajouterOffre(Offre O);
    public void modifierOffre(Offre O);
    public void supprimerOffre(Offre O);
   public ObservableList<Offre> afficherOffre();
  


}
