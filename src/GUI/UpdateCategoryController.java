/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
public class UpdateCategoryController implements Initializable {
    
    ServiceCategorie sc = new ServiceCategorie();
    
    
    
    
    @FXML ComboBox<Category> id;
    @FXML TextField marque, modele;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.getItems().addAll(sc.tout());
        
        id.setOnAction(e -> {
            Category c = id.getSelectionModel().getSelectedItem();
            
            if (c != null) {
                marque.setText(c.getMarque());
                modele.setText(c.getModele());
            } else {
                marque.setText("");
                modele.setText("");
            }
            
        });
        
    }
    
    public void submit() {
        Category o = id.getValue();
        
        if (o == null) {
            alert(Alert.AlertType.ERROR, "Choose a Category First");
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
                               TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Modifié avec succés");
            tray.setMessage("Modifié avec succés");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        alert(Alert.AlertType.INFORMATION, "Category updated successfully");
        
        id.setValue(null);
        marque.setText("");
        modele.setText("");
    }
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
    void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }
    
    public void reset() {
        id.setValue(null);
        marque.setText("");
        modele.setText("");
    }
}
