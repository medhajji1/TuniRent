/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import bdd.bdd;
import entities.reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maroo
 */
public class ServiceReponse {
   
        Connection connection;
        public ServiceReponse(){
            connection =bdd.getinstance().get_connection();       
        }
        PreparedStatement ps=null;
    public void ajouter( reponse p) throws SQLException {
        String requete = "INSERT INTO reponse (id_reclamation, message) VALUES (?,?)";
        PreparedStatement pst = connection.prepareStatement(requete);
        pst.setInt(1, p.getId_reclamation());
        pst.setString(2, p.getMessage());
        pst.executeUpdate();
    }

    public void supprimer(int id){
        try {
            String req = "DELETE FROM `reponse` WHERE id_rep = " + id;
            ps=connection.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("reponse a ete supprimer !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(reponse p) throws SQLException {
        String requete = "UPDATE reponse SET id_reclamation=?, message=? WHERE id_rep=?";
        PreparedStatement pst = connection.prepareStatement(requete);
        pst.setInt(1, p.getId_reclamation());
        pst.setString(2, p.getMessage());
        pst.setInt(3, p.getId_rep());
        pst.executeUpdate();
    }

    public List<reponse> getAll() {
        List<reponse> list = new ArrayList<>();
        try {       
            String req ="SELECT reponse.id_rep, reponse.id_reclamation, reponse.message, reclamation.email FROM reponse JOIN reclamation ON reponse.id_reclamation = reclamation.id_reclamation";
            ps=connection.prepareStatement(req);
            try (ResultSet rs = ps.executeQuery(req)) {
                while(rs.next()){
                    String email = rs.getString("email");                    
                    int id_reclamation = rs.getInt("id_reclamation");                    
                    String message = rs.getString("message"); 
                    System.out.println("----Reponse-----");
                    System.out.println("id_reclamation: " + id_reclamation);
                    System.out.println("Email du Client : " + email);
                    System.out.println("message: " + message);
                    System.out.println("------------------------");
                }     
            }
      ps.close();
      connection.close();
   } catch(SQLException se) {
    }
        return list;
    } 
}
