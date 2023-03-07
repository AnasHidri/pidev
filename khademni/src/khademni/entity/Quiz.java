    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khademni.entity;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class Quiz {
    
    private int id ;
    private String nom ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Quiz() {
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", nom=" + nom + '}';
    }
    
    
    
    
}
