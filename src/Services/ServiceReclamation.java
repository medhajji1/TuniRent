/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import bdd.bdd;
import entities.reclamation;
import entities.reclamation.Category;
import entities.reclamation.SeverityLevel;
import entities.reclamation.Status;
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
    public void update(reclamation rec) {
    try {
        String req = "UPDATE reclamation SET status=?, severity_level=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, rec.getStatus().toString());
        ps.setString(2, rec.getSeverityLevel().toString());
        ps.setInt(3, rec.getId());
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
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
                  R.setId(rs.getInt("id"));
                  R.setNom(rs.getString("nom"));
                  R.setEmail(rs.getString("email"));
                  R.setNumtel(rs.getString("numtel"));
                  R.setSujet(rs.getString("sujet"));
                  R.setMessage(rs.getString("message"));
                  Category category = Category.valueOf(rs.getString("category"));
                  R.setCategory(category);
                  Status status = Status.valueOf(rs.getString("status"));
                  R.setStatus(status);
                  SeverityLevel severity = SeverityLevel.valueOf(rs.getString("severity_level"));
                  R.setSeverityLevel(severity);
                  R.setDateSubmitted(rs.getTimestamp("date_submitted").toLocalDateTime());
                  myList.add(R);
              }
            } catch (SQLException ex) {
        System.err.println(ex.getMessage()); 
            }
         return myList;
}
    public boolean modifier(reclamation r) {
         String requeteUpdate = "UPDATE reclamation SET nom=?, email=?, numtel=?, sujet=?, message=?, category=?, status=? WHERE id=?";

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
            String req = "DELETE from reclamation where id="+rec.getId();
            ps=connection.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("reclamation a ete supprimer !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void modifer2(reclamation rec) {
       String requeteUpdate = "UPDATE reclamation SET severity_level=? status=? WHERE id=?";

                    try {
                        PreparedStatement st = connection.prepareStatement(requeteUpdate);         
                        st.setString(1, rec.getSeverityLevel().name());
                        st.setString(2, rec.getStatus().name());
                        st.setInt(3, rec.getId());
                        st.executeUpdate();
                System.out.println("Reclamation modifiée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
}
    public void tri(reclamation r){
    String triseverity = "SELECT * FROM reclamation ORDER BY severity_level ASC";
         try {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(triseverity);

        while (rs.next()) {
            int id = rs.getInt("id");
            SeverityLevel severity = SeverityLevel.valueOf(rs.getString("severity_level"));
            Status status = Status.valueOf(rs.getString("status"));
            String description = rs.getString("description");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }
}
