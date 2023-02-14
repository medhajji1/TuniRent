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
import tn.esprit.entity.Paiement;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class PaiementService implements InterfaceService<Paiement> {
Connection cnx;

public PaiementService() {
    cnx = MaConnection.getInstance().getCnx();
}

@Override
public void ajouter(Paiement t) {
    try {
        String sql = "insert into paiement(idContrat,montant,date,motif)"
                + "values (?,?,?,?)";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, t.getIdContrat());
        ste.setInt(2, t.getMontant());
        ste.setDate(3, (Date) t.getDate());
        ste.setString(4, t.getMotif());
        ste.executeUpdate();
        System.out.println("Paiement ajouté");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

@Override
public List<Paiement> getAll() {
    List<Paiement> paiements = new ArrayList<>();
    try {
        String sql = "select * from paiement";
        Statement ste = cnx.createStatement();
        ResultSet s = ste.executeQuery(sql);
        while (s.next()) {
            Paiement p = new Paiement(s.getInt(1), s.getInt(4), s.getInt(2),
                    s.getDate(3), s.getString("motif"));
            paiements.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return paiements;
}

@Override
public List<Paiement> findById(int id) {
    List<Paiement> paiements = new ArrayList<>();
    try {
        String sql = "select * from paiement where idContrat = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet rs = ste.executeQuery();
        while (rs.next()) {
            Paiement paiement = new Paiement(rs.getInt("idPaiement"), rs.getInt("idContrat"), 
                    rs.getInt("montant"), rs.getDate("date"), rs.getString("motif"));
            paiements.add(paiement);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return paiements;
}

public void supprimerPaiement(Paiement p) {
    String sql = "delete from paiement where idPaiement=?";
    try {
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, p.getIdPaiement());
        ste.executeUpdate();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public void modifierPaiement(Paiement paiement) {
    try {
        String sql = "update paiement set idContrat=?, montant=?, date=?, motif=? where idPaiement=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, paiement.getIdContrat());
        ste.setInt(2, paiement.getMontant());
        ste.setDate(3, (Date) paiement.getDate());
        ste.setString(4, paiement.getMotif());
        ste.setInt(5, paiement.getIdPaiement());
        ste.executeUpdate();
    } catch (SQLException ex) {
                System.out.println(ex.getMessage());

    }
    }

    
}
