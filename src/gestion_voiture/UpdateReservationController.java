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
public class UpdateReservationController implements Initializable{
    
    ServiceVoiture sc = new ServiceVoiture();
    ServiceReservation sr = new ServiceReservation();
    
    @FXML
    ComboBox <Reservation> id;
    
    @FXML
    ComboBox <Voiture> voiture;
    
    @FXML
    DatePicker dd, df;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.getItems().addAll(sr.tout());
        
        id.setOnAction(e -> {
            Reservation r = id.getSelectionModel().getSelectedItem();
            
            if (r != null) {
                voiture.setValue(r.getVoiture());
                dd.setValue(r.getDate_d().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                df.setValue(r.getDate_f().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            } else {
                reset();
            }
            
        });
    }
    
    public void submit() {
        
        Reservation r = id.getValue();
        
        if (r == null) {
            alert(Alert.AlertType.ERROR, "Choose a Reservation First");
            return;
        }
        
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
        
        r.setVoiture(v);
        r.setDate_d(ddd);
        r.setDate_f(ddf);
        
        sr.modifier(r);
    
        TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Modifié avec succés");
            tray.setMessage("Modifié avec succés");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        alert(Alert.AlertType.INFORMATION, "Reservation updated successfully");
        reset();
    }
    
    
    public void reset() {
        dd.setValue(null);
        df.setValue(null);
        voiture.setValue(null);
        id.setValue(null);
    }
    
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
}
