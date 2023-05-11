/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import gestion_voiture.entities.Category;
import gestion_voiture.entities.Voiture;
import gestion_voiture.entities.Reservation;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
import gestion_voiture.services.ServiceReservation;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


//import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author moham
 */
public class Gestion_voiture extends Application {
    
    
    

    
    
    public static void main(String[] args) {
        launch(args);
    }

    
    
    // gestion categorie --
    public static void testCategoryService() throws Exception {
        ServiceCategorie sc = new ServiceCategorie();

        System.out.println("Creating categories...");

       // Category c1 = new Category("bmw", "Clio");
        //Categorie c2 = new Category("Peugeot", "206");
       // Category c3 = new Category("symbole","clio") ; 
          //Categorie c4 = new Category("aaaaa","cliosssss") ; 
           // Category c5 = new Category("symbolaaaaaaaae","clissso") ; 
            //  Category c6 = new Category("symbasaole","clissaso") ; 
                            Category c7 = new Category("bmw","cbm7") ; 

       // sc.ajouter(c2);
        //sc.ajouter(c3);
        //sc.ajouter(c4);
        // sc.ajouter(c5);
       // sc.ajouter(c6);
        sc.ajouter(c7);
        
        System.out.println("afficher  categories...");
        List<Category> categories = sc.tout();
        for (Category c : categories) {
            System.out.println(c);
        }


        System.out.println("Delete category with id 3...");
        sc.supprimer(3);

        System.out.println("Show all categories after delete...");
        categories = sc.tout();
        for (Category c : categories) {
            System.out.println(c);
        }

        System.out.println("Update category with id 1...");
        //c1.setModele("Clio 7");
        //sc.modifier(c3);

        System.out.println("Show all categories after update...");
        categories = sc.tout();
        for (Category c : categories) {
            System.out.println(c);
        }
    }   

    
    // GEStion - voiture    --
    
    
    
    public static void testVoitureService() {
        ServiceVoiture sv = new ServiceVoiture();
        ServiceCategorie sc = new ServiceCategorie();

        List<Category> categories = sc.tout();

        System.out.println("Creating voitures...");

        //Voiture v1 = new Voiture("EE-18-AA", categories.get(0), 10000, "oussema");
        //Voiture v2 = new Voiture("BB-456-BB", categories.get(1), 20000, "Bleu");
        //Voiture v3 = new Voiture("CC-789-CC", categories.get(3), 30000, "oussema");

        //sv.ajouter(v1);
        //sv.ajouter(v2);
        // sv.ajouter(v3);

        System.out.println("Show all voitures...");
        List<Voiture> voitures = sv.tout();
        for (Voiture v : voitures) {
            System.out.println(v);
        }

        System.out.println("Delete voiture with immatricule BB-456-BB...");
        //sv.supprimer("AA-123-AA");

        System.out.println("Show all voitures after delete...");
        voitures = sv.tout();
        for (Voiture v : voitures) {
            System.out.println(v);
        }

        System.out.println("Update voiture with immatricule AA-123-AA...");
        //v1.setKilometrage(15000);
        //sv.modifier(v1);

        System.out.println("Show all voitures after update...");
        voitures = sv.tout();

        for (Voiture v : voitures) {
            System.out.println(v);
        }
    }
    
    
    // gestion - reservation //
    
    
    
     public static void testReservationService() {
        ServiceReservation sr = new ServiceReservation();
        ServiceVoiture sv = new ServiceVoiture();
        ServiceCategorie sc = new ServiceCategorie();

        List<Voiture> voitures = sv.tout();


        System.out.println("Creating reservations...");

        Reservation r1 = new Reservation(voitures.get(0), new java.util.Date(), new java.util.Date());
       

        //sr.ajouter(r1);
     

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
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            DesignController controller = new DesignController();
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root, 1545, 1080);
            
            stage.setTitle("Tunirent");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

