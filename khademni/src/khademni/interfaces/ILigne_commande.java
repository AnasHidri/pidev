/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import java.util.List;
import khademni.entity.Ligne_commande;
import khademni.entity.Panier;

/**
 *
 * @author mikea
 */
public interface ILigne_commande {
    
    public void ajouterCommande(Ligne_commande p);
    public void ViderCommande(Panier p);
    public void supprimerCommande(Ligne_commande p);
   public List<Ligne_commande> afficherLigneCommande();
   public List<Ligne_commande> afficherMesFormation();
   public void modifierCommande(Ligne_commande p);
   
}
