package gestion_voiture.services;

import gestion_voiture.entities.Category;
import gestion_voiture.entities.Reservation;
import gestion_voiture.entities.Voiture;
import gestion_voiture.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moham
 */
public class ServiceReservation implements IService<Reservation, Integer> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reservation o) {
       try {
            String req = "INSERT INTO reservation (voiture, date_d, date_f) VALUES (?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, o.getVoiture().getImmatriculation());
            st.setDate(2, new java.sql.Date(o.getDate_d().getTime()));
            st.setDate(3, new java.sql.Date(o.getDate_f().getTime()));
            st.executeUpdate();
            System.out.println("Reservation created !");
            
            req = "UPDATE voiture SET booked = true WHERE immatriculation = '" + o.getVoiture().getImmatriculation() + "'";
            Statement stt = cnx.createStatement();
            stt.executeUpdate(req);
            System.out.println("Voiture updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Integer id) {
         try {
            String req = "DELETE FROM reservation WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservation o) {
        try {
            String req = "UPDATE reservation SET voiture = ?, date_d = ?, date_f = ? WHERE reservation.`id` = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, o.getVoiture().getImmatriculation());
            st.setDate(2, new java.sql.Date(o.getDate_d().getTime()));
            st.setDate(3, new java.sql.Date(o.getDate_f().getTime()));
            st.setInt(4, o.getId());
            st.executeUpdate();
            System.out.println("Reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> tout() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "SELECT marque, modele, categorie as category_id, immatriculation, couleur, image, kilometrage, reservation.id as reservation_id, date_d, date_f FROM reservation, voiture, category WHERE reservation.voiture = voiture.immatriculation AND voiture.categorie = category.id ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Category c = new Category(rs.getInt("category_id"), rs.getString("marque"), rs.getString("modele"));
                Voiture v = new Voiture(rs.getString("immatriculation"), c, rs.getInt("kilometrage"), rs.getString("couleur"), rs.getString("image"));
                Reservation r = new Reservation(rs.getInt("reservation_id"), v, new java.util.Date(rs.getDate("date_d").getTime()), new java.util.Date(rs.getDate("date_f").getTime()));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    
    
    @Override
    public Reservation one(Integer id) {
        try {
            String req = "SELECT kilometrage, marque, modele    , immatriculation, couleur, image, reservation.id as reservation_id, date_d, date_f FROM reservation, voiture WHERE reservation.voiture = voiture.immatriculation and id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Category c = new Category(rs.getInt("category_id"), rs.getString("marque"), rs.getString("modele"));
                Voiture v = new Voiture(rs.getString("immatriculation"), c, rs.getInt("kilometrage"), rs.getString("couleur"), rs.getString("image"));
                Reservation r = new Reservation(rs.getInt("reservation_id"), v, new java.util.Date(rs.getDate("date_d").getTime()), new java.util.Date(rs.getDate("date_f").getTime()));
                return r;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public int byInterval(Date from, Date to, String v) {
        int count = 0;
        try {
            String req = "SELECT * FROM reservation WHERE (date_d >= ? OR date_f <= ?) AND voiture = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setDate(1, new java.sql.Date(from.getTime()));
            st.setDate(2, new java.sql.Date(to.getTime()));
            st.setString(3, v);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    
    
}