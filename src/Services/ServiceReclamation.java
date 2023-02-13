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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            String req ="INSERT INTO reclamation(nom,email,numtel,sujet,message) values ('"+p.getNom()+"', "
                    + "'"+p.getEmail()+"', '"+p.getNumtel()+"', '"+p.getSujet()+"', '"+p.getMessage()+"')";
            ps=connection.prepareStatement(req);
            ps.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(reclamation p) {
        try {
            String req = "insert into demande_maintenance(nom,email,numtel,sujet,message) values (?,?,?,?,?)";
            ps=connection.prepareStatement(req);
            ps.setString(1, p.getNom());
        ps.setString(2, p.getEmail());
        ps.setString(3, p.getNumtel());
        ps.setString(4, p.getSujet());
        ps.setString(5, p.getMessage());
            ps.executeUpdate();
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

    public void modifier(reclamation r) {
        String requeteUpdate = "UPDATE reclamation SET nom=?, email=?, numtel=?, sujet=?, message=? WHERE id_reclamation=?";

            try {
            PreparedStatement st = connection.prepareStatement(requeteUpdate);
            st.setString(1, r.getNom());
            st.setString(2, r.getEmail());
            st.setString(3, r.getNumtel());
            st.setString(4, r.getSujet());
            st.setString(5, r.getMessage());
            st.setInt(6, r.getId());
            st.executeUpdate();
            System.out.println("Reclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }

    public List<reclamation> getAll() {
        List<reclamation> list = new ArrayList<>();
        try {       
            String req = "Select * from reclamation";
            ps=connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
         String nom = rs.getString("nom");
         String email = rs.getString("email");
         String numtel = rs.getString("numtel");
         String sujet = rs.getString("sujet");
         String message = rs.getString("message");
         System.out.println("nom: " + nom);
         System.out.println("email: " + email);
         System.out.println("numtel: " + numtel);
         System.out.println("sujet: " + sujet);
         System.out.println("message: " + message);
         System.out.println("------------------------");
      }
      rs.close();
      ps.close();
      connection.close();
   } catch(SQLException se) {
    }
        return list;
    }   
}
