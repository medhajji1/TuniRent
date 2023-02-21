/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.entities.Categorie;
import gestion_voiture.services.ServiceCategorie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 *
 * @author moham
 */


/////////////
public class CreateCategorieController {
    ServiceCategorie sc = new ServiceCategorie();
    
    Alert _alert = new Alert(AlertType.NONE);
    
    @FXML TextField marque, modele;
    
    public void submit() {
      String mq = marque.getText();
      String md = modele.getText();
      
      if (mq.length() == 0) {
          alert(AlertType.ERROR, "marque is required");
          return;
      }
      
      if (md.length() == 0) {
          alert(AlertType.ERROR, "modele is required");
          return;
      }
      
      Categorie c = new Categorie(mq, md);
      sc.ajouter(c);
      alert(AlertType.INFORMATION, "successfully ");
      marque.setText("");
      modele.setText("");
    }
    
    public void reset() {
        marque.setText("");
        modele.setText("");
    }
    
    void alert(AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }

}
