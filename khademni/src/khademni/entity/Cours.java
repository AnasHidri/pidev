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
    private byte file;
    
    public Cours(){
    }

    public Cours(int id_formation, byte file) {
        this.id_formation = id_formation;
        this.file = file;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public byte getFile() {
        return file;
    }

    public void setFile(byte file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Cours{" + "id_cours=" + id_cours + ", id_formation=" + id_formation + ", file=" + file + '}';
    }
    
    
    
}