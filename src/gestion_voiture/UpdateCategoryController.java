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
import javafx.scene.control.TextField;

/**
 *
 * @author moham
 */
public class UpdateCategoryController {
    
    ServiceCategorie sc = new ServiceCategorie();
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    
    @FXML TextField marque, modele, id;
    
    
    public void find() {
        Categorie o = getCategory();
        
        if (o == null) {
            return;
        }
        
        marque.setText(o.getMarque());
        modele.setText(o.getModele());
        
        
    }
    
    public void submit() {
        Categorie o = getCategory();
        
        if (o == null) {
            return;
        }
        
        String mq = marque.getText();
        String md = modele.getText();
      
        if (mq.length() == 0) {
          alert(Alert.AlertType.ERROR, "marque is required");
          return;
        }
      
        if (md.length() == 0) {
          alert(Alert.AlertType.ERROR, "modele is required");
          return;
        }
      
        o.setMarque(mq);
        o.setModele(md);
        sc.modifier(o);
        
        alert(Alert.AlertType.INFORMATION, "Category updated successfully");
        
        id.setText("");
        marque.setText("");
        modele.setText("");
    }
    
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
    
    
    private Categorie getCategory() {
        String x = id.getText();
        if (x.length() == 0) {
            alert(Alert.AlertType.ERROR, "ID is required");
            return null;
        }
        
        int _id ;
        try {
            _id = Integer.parseInt(x);
        }
        catch (Exception e) {
            alert(Alert.AlertType.ERROR, "ERROR ID");
            return null;
        }
        Categorie o = sc.one(_id);
        
        if (o == null) {
            alert(Alert.AlertType.ERROR, "Category does not exist");
            return null;
        }
        
        return o;
    }
    
    public void reset() {
        id.setText("");
        marque.setText("");
        modele.setText("");
    }
}
