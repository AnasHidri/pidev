/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;

import java.util.Objects;

/**
 *
 * @author CYBERLAND
 */
public class Candidature {
    private int id_candidature;
    private int id_user;
    private int id_offre;
    private String etat;

    public Candidature() {
    }

    public Candidature(int id_candidature,   int id_user,int id_offre, String etat) {
        this.id_candidature = id_candidature;
        this.id_offre = id_offre;
        this.id_user = id_user;
        
        this.etat = etat;
    }

    public Candidature(int id_offre,int id_user,  String etat) {
        this.id_offre = id_offre;
        this.id_user = id_user;
        
        this.etat = etat;
    }

    public int getId_candidature() {
        return id_candidature;
    }

   

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_offre() {
        return id_offre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id_candidature;
        hash = 53 * hash + this.id_user;
        hash = 53 * hash + this.id_offre;
        hash = 53 * hash + Objects.hashCode(this.etat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Candidature other = (Candidature) obj;
        if (this.id_candidature != other.id_candidature) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_offre != other.id_offre) {
            return false;
        }
        return Objects.equals(this.etat, other.etat);
    }

    @Override
    public String toString() {
        return "Candidature{" + "id_candidature=" + id_candidature + ", id_user=" + id_user + ", id_offre=" + id_offre + ", etat=" + etat + '}';
    }

   


   
   
}
