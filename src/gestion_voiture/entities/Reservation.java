package gestion_voiture.entities;

import java.util.Date;
/**
 *
 * @author moham
 */
 public class Reservation {
    
    private int id;
    private Voiture voiture;
    private Date date_d, date_f;
    


    public Reservation(int id, Voiture voiture, Date date_d, Date date_f) {
        this.id = id;
        this.voiture = voiture;
        this.date_d = date_d;
        this.date_f = date_f;
    }

    public Reservation(Voiture voiture, Date date_d, Date date_f) {
        this.voiture = voiture;
        this.date_d = date_d;
        this.date_f = date_f;
    }    

    public int getId() {
        return id;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }
    
   
    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Date getDate_d() {
        return date_d;
    }

    public void setDate_d(Date date_d) {
        this.date_d = date_d;
    }

    public Date getDate_f() {
        return date_f;
    }

    public void setDate_f(Date date_f) {
        this.date_f = date_f;
    }


    @Override
    public String toString() {
        return voiture + ", date_d=" + date_d + ", date_f=" + date_f;
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
        final Reservation other = (Reservation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}