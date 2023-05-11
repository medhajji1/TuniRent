/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
import gestion_voiture.entities.Voiture;
import gestion_voiture.entities.Category;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author moham
 */
public class CreateVoitureController implements Initializable {
    
    ServiceVoiture sv = new ServiceVoiture();
    ServiceCategorie sc = new ServiceCategorie();
    
    @FXML TextField coulour, kilometrage, immatriculation, image;
    
    @FXML ComboBox<Category> categoriebox;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Category> all = sc.tout();
        categoriebox.getItems().addAll(all);
    }
    
    public void submit() throws MessagingException {
        
        String clr = coulour.getText();
        String imt = immatriculation.getText();
        String klm = kilometrage.getText();
        Category ctg = (Category)categoriebox.getValue();
        String img = image.getText();
        
        
        if (clr.length() == 0) {
            alert(Alert.AlertType.ERROR, "Coulour is required");
            return;
        }
        
        if (imt.length() == 0) {
            alert(Alert.AlertType.ERROR, "Immatriculation is required");
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
        
        Category c = sc.one(categorie);
        
        System.out.println(c);
        
        if (c == null) {
            alert(Alert.AlertType.ERROR, "Categorie does not exist");
            return;
        }
        
        Voiture v = sv.one(imt);
        
        if (v != null) {
            alert(Alert.AlertType.ERROR, "Voiture already exists");
            return;
        }
        
        v = new Voiture(imt, c, km, clr, img);
        System.out.println(v);
        sv.ajouter(v);
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.SLIDE;
            tray.setAnimationType(type);
            tray.setTitle("Ajouté avec succés");
            tray.setMessage("Ajouté avec succés");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        alert(Alert.AlertType.INFORMATION, "Voiture Added successfully");
         sendMail("mohamedali.lassoued@esprit.tn", "Dépense ajouté avec succées", "Ajouté avec succées");
        
        reset();
    }
    
    public void reset() {
        coulour.setText("");
        immatriculation.setText("");
        kilometrage.setText("");
        categoriebox.setValue(null);
        image.setText("");
    }
    
    public static void sendMail(String recipient,String Subject,String Text) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "mohamedhadji603@gmail.com";
        String password = "svjhlzjcnxisriga";
        Session session = Session.getInstance(properties, new Authenticator() {
             @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            
        Message message = prepareMessage(session, myAccountEmail, recipient,Subject,Text);

        javax.mail.Transport.send(message);
        System.out.println("Message sent successfully");
    }  
   
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient,String Subject,String Text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(Subject);
            message.setText(Text);
            return message;
        } catch (MessagingException ex) {
          
        }
        return null;} 
    
    
    
    Alert _alert = new Alert(Alert.AlertType.NONE);
    
     void alert(Alert.AlertType type, String msg) {
        _alert.setAlertType(type);
        _alert.setHeaderText(msg);
        _alert.setContentText(null);
        _alert.show();
    }

}
