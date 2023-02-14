package gestion_voiture.entities;

/**
 *
 * @author moham
 */
public class Voiture {

    private String immatriculation;
    private Categorie categorie;
    private int kilometrage;
    private String couleur;

    public Voiture(String immatriculation, Categorie categorie, int kilometrage, String couleur) {
        this.immatriculation = immatriculation;
        this.categorie = categorie;
        this.kilometrage = kilometrage;
        this.couleur = couleur;
    }

    public String getImmatriculation() {
        return immatriculation;
    }


    public Categorie getCategorie() {
        return categorie;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Voiture{" + "immatriculation=" + immatriculation + ", categorie=" + categorie + ", kilometrage=" + kilometrage + ", couleur=" + couleur + '}';
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