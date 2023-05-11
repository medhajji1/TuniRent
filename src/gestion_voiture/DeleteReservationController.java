/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import gestion_voiture.entities.Reservation;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceReservation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author moham
 */
public class DeleteReservationController implements Initializable {
    
    ServiceReservation sr = new ServiceReservation();
    
    
    @FXML
    ComboBox<Reservation> reservation;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservation.getItems().addAll(sr.tout());
    }
    
    public void submit() {
        Reservation r = reservation.getValue();
        
        if (r == null) {
            alert(Alert.AlertType.ERROR, "Choose Reservation First");
            return;
        }
     
        sr.supprimer(r.getId());
        
        TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Supprimé avec succés");
            tray.setMessage("Supprimé avec succés");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        alert(Alert.AlertType.INFORMATION, "Category Deleted Successfully");
        
        reset();
    }
    
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
    
    public void reset() {
        reservation.setValue(null);
    }
}
