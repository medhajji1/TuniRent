/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunirentreclamation;

import Services.ServiceReclamation;
import Services.ServiceReponse;
import entities.reclamation;
import entities.reponse;
import java.sql.SQLException;

/**
 *
 * @author maroo
 */
public class TuniRentReclamation {

    public static void main(String[] args) throws SQLException {
        
        reclamation R1 = new reclamation(35,"oussema1", "mohamed@gmail.com", "22789456", "critique d utilisateur", "j ai probleme avec un locataire");
        //reclamation R2 = new reclamation(35,"lebronjames", "labronjames@gmail.com", "90158855", "critique d utilisateur", "j ai probleme avec un locataire");
        
        ServiceReclamation sp = new ServiceReclamation();
        
        //sp.ajouter(R1);
        //sp.ajouter(R2);
        //sp.supprimer(35);
        //sp.modifier(R1);
        //sp.getAll(); 
        
        reponse Rp1 = new reponse(34,"Merci pour votre message. Nous allons étudier votre réclamation et nous vous contacterons sous peu.");
        reponse Rp2 = new reponse(26,8,"Un email a ete envoyer ");
        ServiceReponse sr = new ServiceReponse();
        
        sr.ajouter(Rp1);
        //sr.modifier(Rp2);
        //sr.supprimer(3);
        //sr.getAll();
    }
    
}
