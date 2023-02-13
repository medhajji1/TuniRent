/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Objects;


/**
 *
 * @author maroo
 */
public class reclamation {
    private int id;
    private String nom, email,sujet,message,numtel;
    
    public reclamation() {
    }
    public reclamation(int id, String nom, String email, String numtel, String sujet, String message) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.sujet = sujet;
        this.message = message;
    }
    public reclamation(String nom, String email, String numtel, String sujet, String message) {
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
        this.sujet = sujet;
        this.message = message;
    }
    

    public int getId() {
        return id;
    }

    public String getNumtel() {
        return numtel;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getSujet() {
        return sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "reclamation{ "+ "nom=" + nom + ", email=" + email + ", numtel=" + numtel + ", sujet=" + sujet + ", message=" + message + '}';
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
        final reclamation other = (reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.numtel != other.numtel) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

}