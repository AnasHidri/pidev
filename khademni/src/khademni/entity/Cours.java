/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khademni.entity;

/**
 *
 * @author hmoud
 */
public class Cours {
    
    
    private int id_cours,id_formation;
    private String file, titre, description;

    public Cours(int id_formation, String file, String titre, String description) {
        this.id_formation = id_formation;
        this.file = file;
        this.titre = titre;
        this.description = description;
    }
    
    public Cours(String titre, String description, String file, int id_cours) {
        this.titre = titre;
        this.description = description;
        this.file = file;
        this.id_cours = id_cours;
    }
    
    public Cours(){
    }
    
    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }
/*
    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }*/

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cours{" + "id_cours=" + id_cours + ", file=" + file + ", titre=" + titre + ", description=" + description + '}';
    }

    public String getCoursByFormation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}