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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author moham
 */
public class DeleteVoitureController implements Initializable {
    ServiceCategorie sc = new ServiceCategorie();
    ServiceVoiture sv = new ServiceVoiture();
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    @FXML ComboBox<Voiture> immatriculation;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Voiture> all = sv.tout();
        immatriculation.getItems().addAll(all);
    }
    
    public void submit() {
    
        Voiture o = immatriculation.getValue();
        
        if (o == null) {
            alert(Alert.AlertType.ERROR, "Choose Voiture First");
            return;
        }
    
        sv.supprimer(o.getImmatriculation());
        
        alert(Alert.AlertType.INFORMATION, "Voiture Deleted Successfully");
        immatriculation.setValue(null);
    }
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
    
    public void reset() {
        immatriculation.setValue(null);
    }
}
