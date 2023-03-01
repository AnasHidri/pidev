/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Client extends Utilisateur{
    private float solde;
private String cv ;

    public Client(String nom, String prenom, String login, String password, String role, String mail, String domaine,float solde, String cv) {
        super(nom, prenom, login, password, role, mail, domaine);
        this.solde = solde;
        this.cv = cv;
    }

    public Client(int id, String nom, String prenom, String login, String password, String role, String mail, String domaine,float solde, String cv, String etat, String image) {
        super(id, nom, prenom, login, password, role, mail, domaine, etat, image);
        this.solde = solde;
        this.cv = cv;
    }
    
    

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return super.toString()+"Client{" + "solde=" + solde + ", cv=" + cv + '}';
    }
    

}