/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture.services;

import gestion_voiture.entities.Categorie;
import gestion_voiture.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moham
 */
public class ServiceCategorie implements IService<Categorie, Integer> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Categorie o) {
       try {
            String req = "INSERT INTO `categorie` (`marque`, `modele`) VALUES (?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, o.getMarque());
            st.setString(2, o.getModele());
            st.executeUpdate();
            System.out.println("Categorie created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimer(Integer id) {
         try {
            String req = "DELETE FROM categorie WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categorie o) {
        try {
            String req = "UPDATE categorie SET marque = '" + o.getMarque() + "', modele = '" + o.getModele() + "' WHERE categorie.`id` = " + o.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> tout() {
        List<Categorie> list = new ArrayList<>();
        
        try {
            String req = "Select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Categorie o = new Categorie(rs.getInt(1), rs.getString("marque"), rs.getString("modele"));
                list.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public Categorie one(Integer id) {
        try {
            String req = "Select * from categorie where id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Categorie o = new Categorie(rs.getInt(1), rs.getString("marque"), rs.getString("modele"));
                return o;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
}