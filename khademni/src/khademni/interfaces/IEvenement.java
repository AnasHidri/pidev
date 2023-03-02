/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import khademni.entity.Evenement;
import java.sql.Date;

/**
 *
 * @author user
 * @param <T>
 */
public interface IEvenement<T> {
    
    public void ajouterEvenement(Evenement e);
    public void modifierEvenement(Date date_debut, Date date_fin, String titre,String description,String lieu, Evenement e);
    public void supprimerEvenement( Evenement e);
    public ObservableList<Evenement> getAll();
}