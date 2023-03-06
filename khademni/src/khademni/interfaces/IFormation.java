/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package khademni.interfaces;

import khademni.entity.Formation;

/**
 *
 * @author hmoud
 */
public interface IFormation {
    public void ajouterFormation(Formation f);
    public void modifierFormation(Formation f);
    public void supprimerFormation(Formation f);
    public void afficherFormation();
}
