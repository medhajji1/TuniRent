/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;


import java.time.LocalDate;
import java.util.Date;
import tn.esprit.entity.Contrat;
import tn.esprit.entity.Paiement;
import tn.esprit.services.ContratService;
import tn.esprit.services.PaiementService;
import tn.esprit.services.PersonneService;

/**
 *
 * @author Fayechi
 */
public class PiDev3A26 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        PaiementService ps = new PaiementService();
        ContratService cs = new ContratService();
        //cs.supprimer(1);
        
        
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        
<<<<<<< HEAD
        //Paiement p1 = new Paiement(1,200,sqlDate,"motif");
        //Paiement p2 = new Paiement(3,1,5500,sqlDate,"fedi");
=======
        Paiement p1 = new Paiement(1,200,sqlDate,"motif");
        Paiement p2 = new Paiement(3,1,5500,sqlDate,"fedi");
>>>>>>> a9f2272f143766b9ac53dfea451dfae668a5dc1c
        ///Contrat c1 = new Contrat(1,1,1,sqlDate,"motif");
        
        
        
       //cs.ajouter(c1);
<<<<<<< HEAD
       //ps.ajouter(p2);
=======
       ps.ajouter(p2);
>>>>>>> a9f2272f143766b9ac53dfea451dfae668a5dc1c
        
        
        
        System.out.println(ps.getAll());
        //System.out.println(cs.getAll());
           // ps.modifier(p2);      
<<<<<<< HEAD
        //ps.supprimer(3);
=======
        ps.supprimer(3);
>>>>>>> a9f2272f143766b9ac53dfea451dfae668a5dc1c
    }

}
