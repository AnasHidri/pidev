/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.services;

import java.util.List;
import javafx.collections.ObservableList;
import khademni.entity.Formation;

/**
 *
 * @author hmoud
 */
public interface InterfaceService<T>  {
      public void ajouterFormation(Formation f);
      public void modifierFormation(Formation f);
      public void supprimerFormation(Formation f);
      public ObservableList<Formation> afficherFormation();

    //public void afficherFormation(Formation f);    
    //public List<T> getAll();

}
