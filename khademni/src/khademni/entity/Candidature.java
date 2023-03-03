/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;

import java.sql.Date;
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

    private String titre ,description,adresse_societe,domaine_offre,nom,prenom,email;
    private Date date_debut,date_limite;

    public Candidature(String titre, String description, String adresse_societe, String domaine_offre, Date date_debut, Date date_limite) {
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
        this.date_debut = date_debut;
        this.date_limite = date_limite;
    }

    public Candidature(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
 
     

    public Candidature() {
    }

    public Candidature(String etat) {
        this.etat = etat;
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

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getAdresse_societe() {
        return adresse_societe;
    }

    public String getDomaine_offre() {
        return domaine_offre;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getemail() {
        return email;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_limite() {
        return date_limite;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdresse_societe(String adresse_societe) {
        this.adresse_societe = adresse_societe;
    }

    public void setDomaine_offre(String domaine_offre) {
        this.domaine_offre = domaine_offre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_limite(Date date_limite) {
        this.date_limite = date_limite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id_candidature;
        hash = 29 * hash + this.id_user;
        hash = 29 * hash + this.id_offre;
        hash = 29 * hash + Objects.hashCode(this.etat);
        hash = 29 * hash + Objects.hashCode(this.titre);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.adresse_societe);
        hash = 29 * hash + Objects.hashCode(this.domaine_offre);
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.prenom);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.date_debut);
        hash = 29 * hash + Objects.hashCode(this.date_limite);
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
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.adresse_societe, other.adresse_societe)) {
            return false;
        }
        if (!Objects.equals(this.domaine_offre, other.domaine_offre)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        return Objects.equals(this.date_limite, other.date_limite);
    }

    @Override
    public String toString() {
        return "Candidature{" + "id_candidature=" + id_candidature + ", id_user=" + id_user + ", id_offre=" + id_offre + ", etat=" + etat + ", titre=" + titre + ", description=" + description + ", adresse_societe=" + adresse_societe + ", domaine_offre=" + domaine_offre + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", date_debut=" + date_debut + ", date_limite=" + date_limite + '}';
    }

  

   
   


   
   
}
