package gestion_voiture.entities;

/**
 *
 * @author moham
 */
public class Voiture {

    private String immatriculation;
    private Category categorie;
    private int kilometrage;
    private String couleur;
    private String image;
    

    public Voiture(String immatriculation, Category categorie, int kilometrage, String couleur, String image) {
        this.immatriculation = immatriculation;
        this.categorie = categorie;
        this.kilometrage = kilometrage;
        this.couleur = couleur;
        this.image = image;
    }

    public String getImmatriculation() {
        return immatriculation;
    }


    public Category getCategorie() {
        return categorie;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public String getCouleur() {
        return couleur;
    }
    
    public String getImage() {
        return this.image;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setCategorie(Category categorie) {
        this.categorie = categorie;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return immatriculation + "(" + categorie + ", " + kilometrage + "km, couleur=" + couleur + ")" ;
    }


    @Override
    public int hashCode() {
        return immatriculation.length();
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
        final Voiture other = (Voiture) obj;
        if (this.immatriculation != other.immatriculation) {
            return false;
        }
        return true;
    }

}