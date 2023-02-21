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
public class DeleteCategoryController {
    
    ServiceCategorie sc = new ServiceCategorie();
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    
    
    @FXML TextField id;
    
    public void submit() {
    
        Categorie o = getCategory();
        
        if (o == null) {
            return;
        }
    
        sc.supprimer(o.getId());
        
        alert(Alert.AlertType.INFORMATION, "Category Deleted Successfully");
        id.setText("");
        
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
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
 
    
    public void reset() {
        id.setText("");
    }
}
