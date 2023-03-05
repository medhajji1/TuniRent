/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entity.Contrat;
import tn.esprit.entity.Paiement;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class ContratService implements InterfaceService<Contrat> {

    Connection cnx;

    public ContratService() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Contrat t) {
        try {
            String sql = "insert into contrat(idContrat,date,idReservation,idProprietaire,idLocataire,motif)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getIdContract());
            ste.setDate(2, (java.sql.Date) t.getDate());
            ste.setInt(3, t.getIdReservation());
            ste.setInt(4, t.getIdProprietaire());
            ste.setInt(5, t.getIdLocataire());
            ste.setString(6, t.getMotif());
            ste.executeUpdate();
            System.out.println("Contrat ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Contrat> getAll() {
        List<Contrat> contrats = new ArrayList<>();
        try {
            String sql = "select * from contrat";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                
                
                Contrat c;
                c = new Contrat(s.getInt("idContrat"),
                        s.getInt("idReservation"), s.getInt("idProprietaire"),
                        s.getInt("idLocataire"),s.getDate("date"), s.getString("motif"));
                contrats.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return contrats;
    }

    @Override
    public List<Contrat> findById(int id) {
    List<Contrat> contrats = new ArrayList<>();
    try {
        String sql = "select * from contrat where idContrat = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet s = ste.executeQuery();
        while (s.next()) {
            Contrat c = new Contrat(s.getInt("idContrat"),
                        s.getInt("idReservation"), s.getInt("idProprietaire"),
                        s.getInt("idLocataire"),s.getDate("date"), s.getString("motif"));
            contrats.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return contrats;
    }

    public void supprimer(int id) {
        String sql = "delete from contrat where idContrat=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Contrat t) {
    try {
        String sql = "update contrat set idReservation=?, idProprietaire=?,idLocataire=?, date=?, motif=? where idContrat=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, t.getIdReservation());
        ste.setInt(2, t.getIdProprietaire());
        ste.setDate(4, (Date) t.getDate());
        ste.setInt(3, t.getIdLocataire());
        ste.setInt(6, t.getIdContract());
        ste.setString(5, t.getMotif());
        ste.executeUpdate();
    } catch (SQLException ex) {
                System.out.println(ex.getMessage());

    }    }

}