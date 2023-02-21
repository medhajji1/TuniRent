/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
import gestion_voiture.entities.Voiture;
import gestion_voiture.entities.Categorie;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author moham
 */
public class CreateVoitureController {
    
    ServiceVoiture sv = new ServiceVoiture();
    ServiceCategorie sc = new ServiceCategorie();
    
    @FXML TextField coulour, kilometrage, categorie, immatriculation;
    
    public void submit() {
        
        String clr = coulour.getText();
        String imt = immatriculation.getText();
        String klm = kilometrage.getText();
        String ctg = categorie.getText();
        
        
        
        if (clr.length() == 0) {
            alert(Alert.AlertType.ERROR, "Coulour is required");
            return;
        }
        
        if (imt.length() == 0) {
            alert(Alert.AlertType.ERROR, "Immatriculation is required");
            return;
        }
        
        if (klm.length() == 0) {
            alert(Alert.AlertType.ERROR, "Kilometrage is required");
            return;
        }
        if (ctg.length() == 0) {
            alert(Alert.AlertType.ERROR, "Categorie is required");
            return;
        }
        
        
        int categorie;
        try {
            categorie = Integer.parseInt(ctg);
        }
        catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Categorie shouold be number");
            return;
        }
        
        int km;
        try {
            km = Integer.parseInt(klm);
        }
        catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Kilometrage shouold be number");
            return;
        }
        
        Categorie c = sc.one(categorie);
        
        System.out.println(c);
        
        if (c == null) {
            alert(Alert.AlertType.ERROR, "Categorie does not exist");
            return;
        }
        
        Voiture v = sv.one(imt);
        
        if (v != null) {
            alert(Alert.AlertType.ERROR, "Voiture already exists");
            return;
        }
        
        v = new Voiture(imt, c, km, clr);
        
        sv.ajouter(v);
        alert(Alert.AlertType.INFORMATION, "Voiture Added successfully");
        reset();
    }
    
    public void reset() {
        coulour.setText("");
        immatriculation.setText("");
        kilometrage.setText("");
        categorie.setText("");
    }
    
    
    
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
     void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }

}
