/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;


/**
 *
 * @author ASUS
 */
public class Employeur extends Utilisateur{
    private String nom_societe;

    public Employeur(String nom, String prenom, String login, String password, String role, String mail, String domaine,String nom_societe) {
        super(nom, prenom, login, password, role, mail, domaine);
        this.nom_societe = nom_societe;
    }
    
    public Employeur(int id,String nom, String prenom, String login, String password, String role, String mail, String domaine,String nom_societe,String etat,String image) {
        super(id,nom, prenom, login, password, role, mail, domaine,etat,image);
        this.nom_societe = nom_societe;
    }

    public String getNom_societe() {
        return nom_societe;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }

    @Override
    public String toString() {
        return super.toString()+"Employeur{" + "nom_societe=" + nom_societe + '}';
    }
    
    
}
