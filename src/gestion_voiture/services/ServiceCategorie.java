/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture.services;

import gestion_voiture.entities.Category;
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
public class ServiceCategorie implements IService<Category, Integer> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Category o) {
       try {
            String req = "INSERT INTO `Category` (`marque`, `modele`) VALUES (?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, o.getMarque());
            st.setString(2, o.getModele());
            st.executeUpdate();
            System.out.println("Category created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimer(Integer id) {
         try {
            String req = "DELETE FROM Category WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Category deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Category o) {
        try {
            String req = "UPDATE Category SET marque = '" + o.getMarque() + "', modele = '" + o.getModele() + "' WHERE Category.`id` = " + o.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Category updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Category> tout() {
        List<Category> list = new ArrayList<>();
        
        try {
            String req = "Select * from Category";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Category o = new Category(rs.getInt(1), rs.getString("marque"), rs.getString("modele"));
                list.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public Category one(Integer id) {
        try {
            String req = "Select * from Category where id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Category o = new Category(rs.getInt(1), rs.getString("marque"), rs.getString("modele"));
                return o;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
}