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
public class Formateur extends Utilisateur{
    private String certif ;

    public Formateur(String nom, String prenom, String login, String password, String role, String mail, String domaine,String certif) {
        super(nom, prenom, login, password, role, mail, domaine);
        this.certif = certif;
    }

    public Formateur(int id, String nom, String prenom, String login, String password, String role, String mail, String domaine,String certif, String etat, String image) {
        super(id, nom, prenom, login, password, role, mail, domaine, etat, image);
        this.certif = certif;
    }

    
    
    public String getCertif() {
        return certif;
    }

    public void setCertif(String certif) {
        this.certif = certif;
    }

    public Formateur() {
    }
    
   
}
