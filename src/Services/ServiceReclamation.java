/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import bdd.bdd;
import entities.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maroo
 */
public class ServiceReclamation {
    
        Connection connection;
        public ServiceReclamation(){
            connection =bdd.getinstance().get_connection();
        
        }
         PreparedStatement ps=null;

    public void ajouter(reclamation p) {
        try {
            String req = "INSERT INTO reclamation(nom, email, numtel, sujet, message, category, status, severity_level, date_submitted) " +
             "VALUES ('" + p.getNom() + "', '" + p.getEmail() + "', '" + p.getNumtel() + "', '" + p.getSujet() + "', '" + p.getMessage() + "', " +
             "'" + p.getCategory() + "', '" + p.getStatus() + "', '" + p.getSeverityLevel() + "', NOW())";
            ps=connection.prepareStatement(req);
            ps.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(reclamation r) {
        try {
        String req = "INSERT INTO reclamation(nom, email, numtel, sujet, message, category, status, severity_level, date_submitted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        ps = connection.prepareStatement(req);
        ps.setString(1, r.getNom());
        ps.setString(2, r.getEmail());
        ps.setString(3, r.getNumtel());
        ps.setString(4, r.getSujet());
        ps.setString(5, r.getMessage());
        ps.setString(6, r.getCategory().name());
        ps.setString(7, "open"); // Set status to 'open'
        ps.setString(8, "high"); // Set severity_level to 'high'
        ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now())); // Set date_submitted to current timestamp
        ps.executeUpdate();
        System.out.println("Reclamation ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reclamation` WHERE id_reclamation = " + id;
            ps=connection.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("reclamation a ete supprimer !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public reclamation getreclamation(int id) {
    String req = "SELECT * FROM reclamation WHERE id_reclamation = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            reclamation r = new reclamation();
            r.setId(rs.getInt("id_reclamation"));
            r.setNom(rs.getString("nom"));
            r.setEmail(rs.getString("email"));
            r.setNumtel(rs.getString("numtel"));
            r.setSujet(rs.getString("sujet"));
            r.setMessage(rs.getString("message"));
            return r;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}   
    
    public ObservableList<reclamation> getAll() {
        ObservableList<reclamation>myList=FXCollections.observableArrayList();
            try { 
                String req = "SELECT * FROM reclamation";
                ps=connection.prepareStatement(req);
                    ResultSet rs = ps.executeQuery(req);
              while(rs.next())
              {
                  reclamation R = new reclamation();
                  R.setId(rs.getInt("id_reclamation"));
                  R.setNom(rs.getString("nom"));
                  R.setEmail(rs.getString("email"));
                  R.setNumtel(rs.getString("numtel"));
                  R.setSujet(rs.getString("sujet"));
                  R.setMessage(rs.getString("message"));
                  myList.add(R);
              }
            } catch (SQLException ex) {
        System.err.println(ex.getMessage()); 
            }
         return myList;
}
    public boolean modifier(reclamation r) {
         String requeteUpdate = "UPDATE reclamation SET nom=?, email=?, numtel=?, sujet=?, message=?, category=?, status=? WHERE id_reclamation=?";

                    try {
                        PreparedStatement st = connection.prepareStatement(requeteUpdate);
                        st.setString(1, r.getNom());
                        st.setString(2, r.getEmail());
                        st.setString(3, r.getNumtel());
                        st.setString(4, r.getSujet());
                        st.setString(5, r.getMessage());
                        st.setString(6, r.getCategory().name());
                        st.setString(7, r.getStatus().name());
                        st.setInt(8, r.getId());
                        st.executeUpdate();
                System.out.println("Reclamation modifiée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return true;
    }

    public void supprimer(reclamation rec) {
        
        try {
            String req = "DELETE from reclamation where id_reclamation="+rec.getId();
            ps=connection.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("reclamation a ete supprimer !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
