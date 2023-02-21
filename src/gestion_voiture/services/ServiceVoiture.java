package gestion_voiture.services;

import gestion_voiture.entities.Categorie;
import gestion_voiture.entities.Voiture;
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
public class ServiceVoiture implements IService<Voiture, String> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Voiture o) {
       try {
            String req = "INSERT INTO voiture (immatriculation, kilometrage, couleur, categorie) VALUES (?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, o.getImmatriculation());
            st.setInt(2, o.getKilometrage());
            st.setString(3, o.getCouleur());
            st.setInt(4, o.getCategorie().getId());
            st.executeUpdate();
            System.out.println("Voiture created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimer(String immatriculation) {
         try {
            String req = "DELETE FROM voiture WHERE immatriculation = '" + immatriculation + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Voiture deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Voiture o) {
        try {
            String req = "UPDATE voiture SET kilometrage = '" + o.getKilometrage() + "', couleur = '" + o.getCouleur() + "' WHERE voiture.`immatriculation` = '" + o.getImmatriculation() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Voiture updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Voiture> tout() {
        List<Voiture> list = new ArrayList<>();
        
        try {
            String req = "Select * from voiture, categorie where voiture.categorie = categorie.id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Voiture o = new Voiture(
                    rs.getString("immatriculation"),
                    new Categorie(rs.getInt("id"), rs.getString("marque"), rs.getString("modele")),
                    rs.getInt("kilometrage"),
                    rs.getString("couleur")
                );
                list.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    @Override
    public Voiture one(String id) {
        try {
            String req = "Select * from voiture, categorie where voiture.categorie = categorie.id and voiture.immatriculation = '" + id + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Voiture o = new Voiture(
                    rs.getString("immatriculation"),
                    new Categorie(rs.getInt("id"), rs.getString("marque"), rs.getString("modele")),
                    rs.getInt("kilometrage"),
                    rs.getString("couleur")
                );
                return o;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
}