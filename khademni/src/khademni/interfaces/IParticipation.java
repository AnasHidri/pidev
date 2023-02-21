/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import java.util.List;
import javafx.collections.ObservableList;
import khademni.entity.Evenement;
import khademni.entity.Participation;

/**
 *
 * @author user
 */
public interface IParticipation<T> {
    
    public void ajouterParticipation(Participation p);
    public void modifierParticipation(String status,Participation p);
    public void supprimerParticipation(Evenement e);
    
    public ObservableList<Participation> getAll();
}
