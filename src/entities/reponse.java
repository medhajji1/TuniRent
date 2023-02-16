/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


public class reponse {
    private int id_reclamation;
    private int id_rep;
    private String message;
    
    public reponse(int id_reclamation, int id_rep, String message) {
        this.id_reclamation = id_reclamation;
        this.id_rep = id_rep;
        this.message = message;
    }
    public reponse(int id_reclamation, String message) {
        this.id_reclamation = id_reclamation;
        this.message = message;
    }

    public reponse(String message) {
        this.message = message;
    }
    
    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public int getId_rep() {
        return id_rep;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
