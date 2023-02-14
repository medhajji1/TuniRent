/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Paiement {
    int idPaiement , idContrat,montant;
    Date date;
    String motif;

    public Paiement(int idPaiement, int idContrat, int montant, Date date, String motif) {
        this.idPaiement = idPaiement;
        this.idContrat = idContrat;
        this.montant = montant;
        this.date = date;
        this.motif = motif;
    }

    public Paiement(int idContrat, int montant, Date date, String motif) {
        this.idContrat = idContrat;
        this.montant = montant;
        this.date = date;
        this.motif = motif;
    }

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @Override
    public String toString() {
        return "Paiement{" + "idPaiement=" + idPaiement + ", idContrat=" + idContrat + ", montant=" + montant + ", date=" + date + ", motif=" + motif + '}';
    }
    
    
    
}
