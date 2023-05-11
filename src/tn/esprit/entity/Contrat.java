/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Contrat {
    int idContract,idReservation,idProprietaire,idLocataire;
    Date date;
    String motif;

    public Contrat(int idContract,  Date date,int idReservation, int idProprietaire, int idLocataire, String motif) {
        this.idContract = idContract;
        this.idReservation = idReservation;
        this.idProprietaire = idProprietaire;
        this.idLocataire = idLocataire;
        this.date = date;
        this.motif = motif;
    }

    public Contrat(int idReservation, int idProprietaire, int idLocataire, Date date, String motif) {
        this.idReservation = idReservation;
        this.idProprietaire = idProprietaire;
        this.idLocataire = idLocataire;
        this.date = date;
        this.motif = motif;
    }

    public Contrat(int idContract, int idReservation, int idProprietaire, int idLocataire, Date date, String motif) {
        this.idContract = idContract;
        this.idReservation = idReservation;
        this.idProprietaire = idProprietaire;
        this.idLocataire = idLocataire;
        this.date = date;
        this.motif = motif;
    }

    
    @Override
    public String toString() {
        return "Contrat{" + "idContract=" + idContract + ", idReservation=" + idReservation + ", idProprietaire=" + idProprietaire + ", idLocataire=" + idLocataire + ", date=" + date + ", motif=" + motif + '}';
    }

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(int idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public int getIdLocataire() {
        return idLocataire;
    }

    public void setIdLocataire(int idLocataire) {
        this.idLocataire = idLocataire;
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
    
    
}
