/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import gestion_voiture.entities.Category;
import gestion_voiture.entities.Voiture;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
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
public class UpdateVoitureController implements Initializable {
    ServiceVoiture sv = new ServiceVoiture();
    ServiceCategorie sc = new ServiceCategorie();
    
    @FXML
    ComboBox<Voiture> immatricule;
    
    @FXML
    ComboBox<Category> categorie;
    
    @FXML
    TextField kilometrage, couleur, image;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        immatricule.getItems().addAll(sv.tout());
        
        categorie.getItems().addAll(sc.tout());
        
        immatricule.setOnAction(e -> {
            Voiture v = immatricule.getSelectionModel().getSelectedItem();
            
            if (v != null) {
                kilometrage.setText(Integer.toString(v.getKilometrage()));
                couleur.setText(v.getCouleur());
                image.setText(v.getImage());
                categorie.setValue(v.getCategorie());
            } else {
                kilometrage.setText("");
                couleur.setText("");
                image.setText("");
                categorie.setValue(null);
            }
            
        });
        
    }
    
    public void submit() {
    
    Voiture o = immatricule.getValue();
        
        if (o == null) {
            alert(Alert.AlertType.ERROR, "Choose a Voiture First");
            return;
        }
    
        String clr = couleur.getText();
        String klm = kilometrage.getText();
        Category ctg = (Category)categorie.getValue();
        String img = image.getText();
        
        
        if (clr.length() == 0) {
            alert(Alert.AlertType.ERROR, "Coulour is required");
            return;
        }
        
        if (klm.length() == 0) {
            alert(Alert.AlertType.ERROR, "Kilometrage is required");
            return;
        }
        
        if (ctg == null) {
            alert(Alert.AlertType.ERROR, "Categorie is required");
            return;
        }
        
        if (img.length() == 0) {
            alert(Alert.AlertType.ERROR, "Image is required");
            return;
        }
        
        
        int categorie = ctg.getId();
        
        int km;
        try {
            km = Integer.parseInt(klm);
        }
        catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Kilometrage shouold be number");
            return;
        }
        
        
        o.setCategorie(ctg);
        o.setCouleur(clr);
        o.setImage(img);
        o.setKilometrage(km);
        
        sv.modifier(o);
      
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Modifié avec succés");
            tray.setMessage("Modifié avec succés");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        alert(Alert.AlertType.INFORMATION, "Voiture updated successfully");
        
        reset();
    }
    
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    public void reset() {
        couleur.setText("");
        immatricule.setValue(null);
        kilometrage.setText("");
        categorie.setValue(null);
        image.setText("");
    }
}
