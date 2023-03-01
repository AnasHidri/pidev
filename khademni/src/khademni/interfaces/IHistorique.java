/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import java.util.List;
import khademni.entity.Historique;

/**
 *
 * @author mikea
 */
public interface IHistorique {
    
    public void ajouterHistorique (Historique h);
       public List<Historique> afficherHistorique();
}
