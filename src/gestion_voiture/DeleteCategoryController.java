/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.entities.Category;
import gestion_voiture.services.ServiceCategorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author moham
 */
public class DeleteCategoryController implements Initializable {
    
    ServiceCategorie sc = new ServiceCategorie();
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    @FXML ComboBox<Category> id;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.getItems().addAll(sc.tout());
    }
    
    public void submit() {
    
        Category o = id.getValue();
        
        if (o == null) {
            alert(Alert.AlertType.ERROR, "Choose Category First");
            return;
        }
    
        sc.supprimer(o.getId());
                                       TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Supprimé avec succés");
            tray.setMessage("Supprimé avec succés");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        alert(Alert.AlertType.INFORMATION, "Category Deleted Successfully");
        id.setValue(null);
        
    }
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
 
    
    public void reset() {
        id.setValue(null);
    }
}
