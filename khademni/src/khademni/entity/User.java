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
public class User {
    private int  id_user;
    private String nom,prenom,email;

    public User(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

   

    public int getId_user() {
        return id_user;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id_user;
        hash = 89 * hash + Objects.hashCode(this.nom);
        hash = 89 * hash + Objects.hashCode(this.prenom);
        hash = 89 * hash + Objects.hashCode(this.email);
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
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }
    
    
}
