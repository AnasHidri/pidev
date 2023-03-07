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
public class Note {
    
    private int id_note ,id_question,id_user;
    private float note ;

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getNote() {
        return this.note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" + "id_note=" + id_note + ", id_question=" + id_question + ", id_user=" + id_user + ", note=" + note + '}';
    }

    public Note() {
    }
    
    
}
