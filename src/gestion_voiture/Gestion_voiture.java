/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.entities.Categorie;
import gestion_voiture.entities.Voiture;
import gestion_voiture.entities.Reservation;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
import gestion_voiture.services.ServiceReservation;
import gestion_voiture.utils.DataSource;
import java.util.List;

/**
 *
 * @author moham
 */
public class Gestion_voiture {

    public static void main(String[] args) {
        testReservationService();
        /* ServiceCategorie sc = new ServiceCategorie();
        ServiceVoiture sv = new ServiceVoiture();
        
        Categorie c = new Categorie("lamborgini", "huracan");
        sc.ajouter(c);
       
        
        Categorie c = sc.one(7);
        Voiture v = new Voiture("dgqsdgjq", c, 4750, "purple");
        sv.ajouter(v); */
    }

    public static void testCategoryService() {
        ServiceCategorie sc = new ServiceCategorie();

        System.out.println("Creating categories...");

        Categorie c1 = new Categorie("bmw", "Clio");
        Categorie c2 = new Categorie("Peugeot", "206");
        Categorie c3 = new Categorie("Citroen", "C3");

        sc.ajouter(c1);
        sc.ajouter(c2);
        sc.ajouter(c3);

        System.out.println("Show all categories...");
        List<Categorie> categories = sc.tout();
        for (Categorie c : categories) {
            System.out.println(c);
        }


        System.out.println("Delete category with id 2...");
        sc.supprimer(2);

        System.out.println("Show all categories after delete...");
        categories = sc.tout();
        for (Categorie c : categories) {
            System.out.println(c);
        }

        System.out.println("Update category with id 1...");
        c1.setModele("Clio 4");
        sc.modifier(c1);

        System.out.println("Show all categories after update...");
        categories = sc.tout();
        for (Categorie c : categories) {
            System.out.println(c);
        }
    }   

    public static void testVoitureService() {
        ServiceVoiture sv = new ServiceVoiture();
        ServiceCategorie sc = new ServiceCategorie();

        List<Categorie> categories = sc.tout();

        System.out.println("Creating voitures...");

        Voiture v1 = new Voiture("AA-123-AA", categories.get(0), 10000, "Rouge");
        Voiture v2 = new Voiture("BB-456-BB", categories.get(1), 20000, "Bleu");
        Voiture v3 = new Voiture("CC-789-CC", categories.get(2), 30000, "Vert");

        sv.ajouter(v1);
        sv.ajouter(v2);
        sv.ajouter(v3);

        System.out.println("Show all voitures...");
        List<Voiture> voitures = sv.tout();
        for (Voiture v : voitures) {
            System.out.println(v);
        }

        System.out.println("Delete voiture with immatricule BB-456-BB...");
        sv.supprimer("BB-456-BB");

        System.out.println("Show all voitures after delete...");
        voitures = sv.tout();
        for (Voiture v : voitures) {
            System.out.println(v);
        }

        System.out.println("Update voiture with immatricule AA-123-AA...");
        v1.setKilometrage(15000);
        sv.modifier(v1);

        System.out.println("Show all voitures after update...");
        voitures = sv.tout();

        for (Voiture v : voitures) {
            System.out.println(v);
        }
    }
    
     public static void testReservationService() {
        ServiceReservation sr = new ServiceReservation();
        ServiceVoiture sv = new ServiceVoiture();
        ServiceCategorie sc = new ServiceCategorie();

        List<Voiture> voitures = sv.tout();


        System.out.println("Creating reservations...");

        Reservation r1 = new Reservation(voitures.get(0), new java.util.Date(), new java.util.Date());
        Reservation r2 = new Reservation(voitures.get(1), new java.util.Date(), new java.util.Date());
        Reservation r3 = new Reservation(voitures.get(0), new java.util.Date(), new java.util.Date());

        sr.ajouter(r1);
        sr.ajouter(r2);
        sr.ajouter(r3);

        System.out.println("Show all reservations...");
        List<Reservation> reservations = sr.tout();

        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println("Delete reservation with id 2...");

        sr.supprimer(2);

        System.out.println("Show all reservations after delete...");

        reservations = sr.tout();

        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println("Update reservation with id 1...");

        r1.setDate_d(new java.util.Date());

        sr.modifier(r1);

        System.out.println("Show all reservations after update...");

        reservations = sr.tout();

        for (Reservation r : reservations) {
            System.out.println(r);
        }


    }
}