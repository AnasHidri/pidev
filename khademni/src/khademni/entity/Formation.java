/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;


/**
 *
 * @author hmoud
 */
public class Formation {

    
    private int id_formation,id_user ;
    private String description,titre, domaine_formation;
    private double prix;

    public Formation(int id_user, String titre, String domaine_formation, double prix,  String description) {
        this.id_user=id_user;
        this.description = description;
        this.titre = titre;
        this.domaine_formation = domaine_formation;
        this.prix = prix;
    }

    public Formation(int id_formation, String description, String titre, String domaine_formation, double prix) {
        this.id_formation = id_formation;
        this.description = description;
        this.titre = titre;
        this.domaine_formation = domaine_formation;
        this.prix = prix;
    }
    
    

    
    
    public Formation(){
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }
/*
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
*/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDomaine_formation() {
        return domaine_formation;
    }

    public void setDomaine_formation(String domaine_formation) {
        this.domaine_formation = domaine_formation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    

    @Override
    public String toString() {
        return "Formation{" + "id_formation= " + id_formation + ", description=" + description + ", titre=" + titre + ", domaine_formation=" + domaine_formation + ", prix=" + prix + '}';
    }
    
}
