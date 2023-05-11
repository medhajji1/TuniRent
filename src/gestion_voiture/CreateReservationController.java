/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.entities.*;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceReservation;
import gestion_voiture.services.ServiceVoiture;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author moham
 */
public class CreateReservationController implements Initializable{

    ServiceVoiture sv = new ServiceVoiture();
    ServiceCategorie sc = new ServiceCategorie();
    ServiceReservation sr = new ServiceReservation();
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    @FXML 
    ComboBox<Voiture> voiture;
    
    @FXML
    DatePicker dd, df;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        voiture.getItems().addAll(sv.tout());
    }
    
    public void submit() {
        LocalDate ddl = dd.getValue();
        LocalDate dfl = df.getValue();

        if (ddl == null) {
            alert(Alert.AlertType.ERROR, "Date debut is required");
            return;
        }
        
        if (dfl == null) {
            alert(Alert.AlertType.ERROR, "Date fin is required");
            return;
        }
        
        if (ddl.toEpochDay() > dfl.toEpochDay()) {
            alert(Alert.AlertType.ERROR, "Date fin cant be before Date debut");
            return;
        }
        
        if (ddl.toEpochDay() < LocalDate.now().toEpochDay()) {
            alert(Alert.AlertType.ERROR, "Date debut should not be in the past");
            return;
        }
        
        
        
        Voiture v = voiture.getValue();

        if (v == null) {
            alert(Alert.AlertType.ERROR, "Voiture is required");
            return;
        }
        
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date ddd = Date.from(ddl.atStartOfDay(defaultZoneId).toInstant());
        Date ddf = Date.from(dfl.atStartOfDay(defaultZoneId).toInstant());

        if (sr.byInterval(ddd, ddf, v.getImmatriculation()) > 0) {
            alert(Alert.AlertType.ERROR, "Voiture is already Booked in the specified time period");
            return;
        }
        
        Reservation r = new Reservation(v, ddd, ddf);
        sr.ajouter(r);
        
         TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Ajouté avec succés");
            tray.setMessage("Ajouté avec succés");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
      alert(Alert.AlertType.INFORMATION, "successfully");
        
        reset();
    }
    
    public void reset() {
        voiture.setValue(null);
        dd.setValue(null);
        df.setValue(null);
    }
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
}
