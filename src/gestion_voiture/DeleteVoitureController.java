/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.entities.Categorie;
import gestion_voiture.entities.Voiture;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author moham
 */
public class DeleteVoitureController {
    ServiceCategorie sc = new ServiceCategorie();
    ServiceVoiture sv = new ServiceVoiture();
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    
    
    @FXML TextField immatriculation;
    
    public void submit() {
    
        Voiture o = getVoiture();
        
        if (o == null) {
            return;
        }
    
        sv.supprimer(o.getImmatriculation());
        
        alert(Alert.AlertType.INFORMATION, "Voiture Deleted Successfully");
        immatriculation.setText("");
    }
    
    private Voiture getVoiture() {
        String x = immatriculation.getText();
        if (x.length() == 0) {
            alert(Alert.AlertType.ERROR, "Immatriculation is required");
            return null;
        }
        
        Voiture o = sv.one(x);
        
        if (o == null) {
            alert(Alert.AlertType.ERROR, "Voiture does not exist");
            return null;
        }
        
        return o;
    }
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
 
    
    public void reset() {
        immatriculation.setText("");
    }
}
