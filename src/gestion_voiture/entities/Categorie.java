/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture.entities;

/**
 *
 * @author moham
 */
public class Categorie {
    
    private int id;
    private String marque, modele;
    
    public Categorie(int id, String marque, String modele) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
    }
    
    public Categorie(String marque, String modele) {
        this.marque = marque;
        this.modele = modele;
    }
    

    public int getId() {
        return id;
    }
    
    public String getMarque() {
        return marque;
    }
    
    public String getModele() {
        return modele;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setMarque(String modele) {
        this.modele = modele;
    }
    
    public void setModele(String modele) {
        this.modele = modele;
    }


    @Override
    public String toString() {
        return marque + " " + modele;
    }    
        
    @Override
    public int hashCode() {
        return id;
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
        final Categorie other = (Categorie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
