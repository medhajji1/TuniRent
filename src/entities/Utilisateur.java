/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DELL
 */
/**
 *
 * @author 
 */
public class Utilisateur {
    private int CIN;
    private String nom, prenom, email, motDePasse, numeroTelephone;
    private String typeUtilisateur;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public Utilisateur() {
    }
    public Utilisateur(int CIN, String nom, String prenom, String email, String motDePasse, String numeroTelephone, String typeUtilisateur) {
        if (!typeUtilisateur.equals("client") && !typeUtilisateur.equals("admin")) {
        throw new IllegalArgumentException("Le type d'utilisateur doit être soit 'client' soit 'admin'");
        }
        if (numeroTelephone.length() != 8 || !numeroTelephone.matches("[0-9]+")) {
        throw new IllegalArgumentException("Le numéro de téléphone doit être strictement égal à 8 et ne contenir que des chiffres");
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("L'adresse email n'est pas valide");
        }
        
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.numeroTelephone = numeroTelephone;
        this.typeUtilisateur = typeUtilisateur;
    }

    Utilisateur(String cin, String nom, String prenom, String email, String motDePasse, String numeroTelephone, String typeUtilisateur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
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
    String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    if (!email.matches(regex)) {
        throw new IllegalArgumentException("Adresse email non valide");
    }
    this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
    if (numeroTelephone.length() != 8 || !numeroTelephone.matches("[0-9]+")) {
        throw new IllegalArgumentException("Le numéro de téléphone doit être strictement égal à 8 et ne contenir que des chiffres");
    }
        this.numeroTelephone = numeroTelephone;
    }

    public String getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        if (!typeUtilisateur.equals("client") && !typeUtilisateur.equals("admin")) {
            throw new IllegalArgumentException("Le type d'utilisateur doit être soit 'client' soit 'admin'");
        }
        this.typeUtilisateur = typeUtilisateur;
    }
    @Override
    public String toString() {
    return "Utilisateur{" + "CIN=" + CIN + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse=" + motDePasse + ", numeroTelephone=" + numeroTelephone + ", typeUtilisateur=" + typeUtilisateur + "}";
}
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Utilisateur other = (Utilisateur) obj;
        if (this.CIN != other.CIN) {
            return false;
        }
        return true;
    }

    
    
    
    
}