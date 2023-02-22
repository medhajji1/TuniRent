package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rep;

    @ManyToOne
    @JoinColumn(name = "id_reclamation")
    public reclamation rec;

    private String message;

    public reponse() {
    }

    public reponse(reclamation rec, String message) {
        this.rec = rec;
        this.message = message;
    }
     public reponse(int id_rep, reclamation rec, String message) {
        this.id_rep = id_rep;
        this.rec = rec;
        this.message = message;
    }
     public reponse(String message) {
        this.message = message;
    }
    public reclamation getRec() {
        return rec;
    }

    public void setRec(reclamation rec) {
        this.rec = rec;
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

    @Override
    public String toString() {
        return "reponse{" + "rec=" + rec + ", id_rep=" + id_rep + ", message=" + message + '}';
    }
}