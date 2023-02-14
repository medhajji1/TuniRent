/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entity.Personne;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author Fayechi
 */
public class PersonneService implements InterfaceService<Personne> {

    Connection cnx;

    public PersonneService() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Personne t) {
        try {
            String sql = "insert into personne(nom,prenom,age)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setString(2, t.getPrenom());
            ste.setInt(3, t.getAge());
            ste.executeUpdate();
            System.out.println("Personne ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Personne> getAll() {
        List<Personne> personnes = new ArrayList<>();
        try {
            String sql = "select * from personne";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Personne p = new Personne(s.getInt(1), s.getInt(4),
                        s.getString("nom"), s.getString("prenom"));
                personnes.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    }

    @Override
    public List<Personne> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void supprimerPersonne(Personne p) {
        String sql = "delete from personne where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierPersonne(String nom,Personne p) {
        String sql = "update personne set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2,p.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
