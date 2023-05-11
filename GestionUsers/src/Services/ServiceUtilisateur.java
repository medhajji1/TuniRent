/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Utilisateur;
import database.DataSource;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceUtilisateur implements IService<Utilisateur> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Utilisateur u) {
        try {
            String req = "INSERT INTO `utilisateur` (`CIN`,`nom`, `prenom`,`email`,`motDePasse`,`numeroTelephone`,`typeUtilisateur`) VALUES ('" + u.getCIN() + "', '" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getEmail() + "', '" + u.getMotDePasse() + "', '" + u.getNumeroTelephone() + "','" + u.getTypeUtilisateur() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur creé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(Utilisateur u) {
        try {
            String req = "INSERT INTO `utilisateur` (`nom`, `prenom`, `email`, `motDePasse`, `numeroTelephone`,`typeUtilisateur`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(6, u.getTypeUtilisateur());
            ps.setString(5, u.getNumeroTelephone());
            ps.setString(4, u.getMotDePasse());
            ps.setString(3, u.getEmail());
            ps.setString(2, u.getPrenom());
            ps.setString(1, u.getNom());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int CIN) {
        try {
            String req = "DELETE FROM `utilisateur` WHERE CIN = " + CIN;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param u
     * @return
     */
    public boolean modifier(Utilisateur u) {
        String requeteUpdate = "UPDATE utilisateur SET nom=?, prenom=?, email=?, motDePass=?, numeroTelephone=?,typeUtilisateur=? WHERE CIN=?";

            try {
                PreparedStatement st = cnx.prepareStatement(requeteUpdate);
                st.setString(1, u.getNom());
                st.setString(2, u.getPrenom());
                st.setString(3, u.getEmail());
                st.setString(4, u.getMotDePasse());
                st.setString(5, u.getNumeroTelephone());
                st.setString(6, u.getTypeUtilisateur());
                st.setInt(7, u.getCIN());
                st.executeUpdate();
                System.out.println("Reclamation modifiée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return true;
    }

    @Override
    public ObservableList<Utilisateur> getAll() {
        ObservableList<Utilisateur>myList=FXCollections.observableArrayList();
        try { 
                String req = "SELECT * FROM utilisateur";
               PreparedStatement ps=cnx.prepareStatement(req);
                    ResultSet rs = ps.executeQuery(req);
              while(rs.next())
              {
                  Utilisateur R = new Utilisateur();
                  R.setCIN(rs.getInt("CIN"));
                  R.setNom(rs.getString("nom"));
                  R.setPrenom(rs.getString("prenom"));
                  R.setEmail(rs.getString("email"));
                  R.setMotDePasse(rs.getString("motDePasse"));
                  R.setNumeroTelephone(rs.getString("numeroTelephone"));
                  R.setTypeUtilisateur(rs.getString("typeUtilisateur"));
                  myList.add(R);
              }
            } catch (SQLException ex) {
        System.err.println(ex.getMessage()); 
            }

        return myList;
    }
    public void supprimer(Utilisateur rec) {
        
        try {
            String req = "DELETE from utilisateur where CIN="+rec.getCIN();
          PreparedStatement ps=cnx.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("utilisateur a ete supprimer !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
