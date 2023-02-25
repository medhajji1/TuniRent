/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import Services.ServiceReponse;
import entities.reclamation;
import entities.reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import java.net.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
/**
 * FXML Controller class
 *
 * @author yadii
 */
public class EnvoyerReponseController implements Initializable {

    @FXML
    private AnchorPane anchropane;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btn_ajout;
    reclamation d =null;
    @FXML
    private TextArea obj;
    @FXML
    private Button Annuler;
    String from,to,host,sub,content;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setdm(reclamation d){
 
         this.d=d;
    }
    public void setelementtoupdate(reclamation d){
        this.d=d;
    System.out.println("email :"+ d.toString());
    this.tfEmail.setText(d.getEmail());
    tfEmail.setEditable(false);
    }
    @FXML
private void ajout(ActionEvent event) throws SQLException {
        from ="mohamedhadji603@gmail.com";
        to =tfEmail.getText();
        host="localhost";
        content=obj.getText();
        Properties P = new Properties();
        P.put("mail.smtp.auth","true");
        P.put("mail.smtp.starttls.enable","true");
        P.put("mail.smtp.host","smtp.gmail.com");
        P.put("mail.smtp.port","587");
        Session session = Session.getInstance(P, new javax.mail.Authenticator() {

            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

                return new javax.mail.PasswordAuthentication("mohamedhadji603@gmail.com", "azeqsdwxc,;:!123456");

            }

        });
        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(sub); 
            
            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodypart= new MimeBodyPart();
            textBodypart.setText("Demande de maintenance");
            MimeBodyPart pdfAttachement = new MimeBodyPart();
            //pdfAttachement.attachFile("src/pdfdemande/demamde de maintenance.pdf");
            emailContent.addBodyPart(textBodypart);
            emailContent.addBodyPart(pdfAttachement);
            //m.setText(content);
            m.setContent(emailContent);
            //Transport.send(m);
            System.out.println("email envoyer");
        } catch (Exception e) {
             e.printStackTrace();
        }
        
            
        ServiceReclamation SM = new ServiceReclamation();
    if (d !=null){
        d.setStatus(reclamation.Status.RESOLVED); // set the status to in progress
        d.setSeverityLevel(reclamation.SeverityLevel.HIGH); // set severity level to low
        SM.update(d);
    ServiceReponse rep = new ServiceReponse();
    if (obj.getText().isEmpty()) {
        Alert a = new Alert(Alert.AlertType.WARNING, "Tous les champs sont obligatoires", ButtonType.OK);
        a.showAndWait();
        return;
    }
    reponse p = new reponse(obj.getText());
    p.setRec(d); // Set the reclamation ID in the response object
    rep.ajouter(p);
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Votre demande a été envoyée!", ButtonType.OK);
    a.showAndWait();
    }
}

    @FXML
    private void Annuler(ActionEvent event) {
    ServiceReclamation SM = new ServiceReclamation();
    if (d !=null) {
        try {
            d.setStatus(reclamation.Status.CLOSED); // set the status to closed
            d.setSeverityLevel(reclamation.SeverityLevel.LOW); // set severity level to low
            SM.update(d);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
