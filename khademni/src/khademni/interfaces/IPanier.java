/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import javafx.collections.ObservableList;
import khademni.entity.Panier;
import khademni.entity.Utilisateur;

/**
 *
 * @author mikea
 */
public interface IPanier {
    
     public void ajouterPanier(int u);
    public void modifierPanier(Panier p);
    public void supprimerPanier(Panier p);
    public ObservableList<String> afficherPanier();
   public int affichesomme(Panier p);
   public void payer(Panier p);
}
