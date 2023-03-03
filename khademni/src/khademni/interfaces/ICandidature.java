/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;



/**
 *
 * @author CYBERLAND
 **/
import java.util.List;
import javafx.collections.ObservableList;


public interface ICandidature <Candidature>{
    public void ajouterCandidature(Candidature P);
    public void modifierCandidature(Candidature P);
    public void supprimerCandidature(Candidature P);
    public ObservableList<Candidature> afficherCandidatureEmployeur1();
    public ObservableList<Candidature> afficherCandidatureEmployeur2();
       public ObservableList<Candidature> afficherCandidatureEmployeur3();
    public ObservableList<Candidature> afficherCandidatureClient1();
public ObservableList<Candidature> afficherCandidatureClient2();
    
} 
