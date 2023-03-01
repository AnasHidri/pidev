/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import javafx.collections.ObservableList;
import khademni.entity.Client;
import khademni.entity.Employeur;
import khademni.entity.Formateur;
import khademni.entity.Utilisateur;

/**
 *
 * @author ASUS
 */
public interface IUtilisateurService {  
    public void ajouterEmployeur(Employeur e);
    public void ajouterFormateur(Formateur f);
    public void ajouterClient(Client c);
    public void modifierUtilisateur(Utilisateur u);
    public void supprimerUtilisateur(Utilisateur u);
    public ObservableList<Utilisateur> afficherUtilisateurs();
}