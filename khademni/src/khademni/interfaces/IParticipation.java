/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import java.util.List;
import khademni.entity.Participation;

/**
 *
 * @author user
 */
public interface IParticipation<T> {
    
    public void ajouterParticipation(Participation p);
    public void modifierParticipation(String status,Participation p);
    public void supprimerParticipation(Participation p);
    public List<T> getAll();
}
