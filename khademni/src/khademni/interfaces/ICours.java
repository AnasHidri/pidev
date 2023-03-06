/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import javafx.collections.ObservableList;
import khademni.entity.Cours;

/**
 *
 * @author hmoud
 */
public interface ICours {
      public void ajouterCours(Cours c);
      public void modifierCours(Cours c);
      public void supprimerCours(Cours c);
      public ObservableList<Cours> afficherCours();
}
