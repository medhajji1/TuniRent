/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDateTime;

/**
 *
 * @author maroo
 */
public class reclamation {
    private int id;
    private String nom, email, sujet, message, numtel;
    private Category category;
    private Status status;
    private SeverityLevel severityLevel;
    private LocalDateTime dateSubmitted;

    public reclamation() {
    }

    public reclamation(String nom, String email, String sujet, String message, String numtel) {
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.message = message;
        this.numtel = numtel;
    }

    
    public enum Category {
    QUALITÉ,
    SERVICE,
    FACTURATION
        }
    public enum Status {
    OPEN,
    CLOSED,
    INPROGRESS,
    RESOLVED
}
    public enum SeverityLevel {

        LOW,
        HIGH
        }

    public reclamation(String nom, String email, String sujet, String message, String numtel, Category category, Status status, SeverityLevel severityLevel, String dateSubmitted) {
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.message = message;
        this.numtel = numtel;
        this.category = category;
        this.status = Status.OPEN;
        this.severityLevel = SeverityLevel.HIGH;
        this.dateSubmitted = LocalDateTime.now();
    }
   
    public reclamation(String nom, String email, String sujet, String message, String numtel, Category category) {
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.message = message;
        this.numtel = numtel;
        this.category = category;
    }

    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDateTime dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
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

    public Category getCategory() {
        return category;
    }

    public Status getStatus() {
        return status;
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }
    @Override
    public String toString() {
        return "reclamation{ " +
                "nom=" + nom +
                ", email=" + email +
                ", numtel=" + numtel +
                ", sujet=" + sujet +
                ", message=" + message +
                ", category=" + category +
                ", status=" + status +
                ", severityLevel=" + severityLevel +
                ", dateSubmitted=" + dateSubmitted +
                '}';
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
        return true;
    }
}