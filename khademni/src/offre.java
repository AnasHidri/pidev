
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author CYBERLAND
*/    public class offre {
    private int id_offre ;
    private int id_user;
    private String titre ,description,adresse_societe,domaine_offre;

    public offre() {
    }

    public offre( int id_user,String titre, String description, String adresse_societe, String domaine_offre) {

        this.id_user=id_user;
       
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
    }

    public offre(String titre, String description, String adresse_societe, String domaine_offre) {
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
    }

    public int getId_offre() {
        return id_offre;
    }
    

    public int getId_user() {
        return id_user;
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

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_offre;
        hash = 97 * hash + this.id_user;
        hash = 97 * hash + Objects.hashCode(this.titre);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.adresse_societe);
        hash = 97 * hash + Objects.hashCode(this.domaine_offre);
        return hash;
    }

    
    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", id_user=" + id_user + ", titre=" + titre + ", description=" + description + ", adresse_societe=" + adresse_societe + ", Domaine_offre=" + domaine_offre + '}';
    }

  
    
}
