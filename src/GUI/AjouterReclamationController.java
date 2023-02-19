/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import entities.reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author yadii
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField np;
    @FXML
    private TextField mail;
    @FXML
    private TextField numtel;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea msg;
    @FXML
    private Button btn;
    private reclamation rec;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    private final Pattern nomPattern = Pattern.compile("[a-zA-Z]+\\s[a-zA-Z]+");
    private final Pattern emailPattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    private final Pattern sujetPattern = Pattern.compile("[a-zA-Z ]+");
    private final Pattern messagePattern = Pattern.compile("[a-zA-Z ]+");
    private final Pattern numtelPattern = Pattern.compile("[259]\\d{7}");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          handleKeyType(); 
          btn.setDisable(true);
        np.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!nomPattern.matcher(newValue).matches()) {
                np.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                np.setStyle("-fx-text-fill: green;");
            }
            handleKeyType();
        });

        mail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!emailPattern.matcher(newValue).matches()) {
                mail.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                mail.setStyle("-fx-text-fill: green;");
            }
            handleKeyType();
        });

        sujet.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!sujetPattern.matcher(newValue).matches()) {
                sujet.setStyle("-fx-border-color: red;");
            } else {
                sujet.setStyle("");
            }
            handleKeyType();
        });

        msg.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!messagePattern.matcher(newValue).matches()) {
                msg.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                msg.setStyle("-fx-text-fill: green;");
            }
            handleKeyType();
        });
        
        numtel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!numtelPattern.matcher(newValue).matches()) {
                numtel.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                numtel.setStyle("-fx-text-fill: green;");
            }
            handleKeyType();
        });
    }
    
    @FXML
   private void Addreclamation(ActionEvent event) throws IOException {
        ServiceReclamation sp = new ServiceReclamation();
        if (np.getText().isEmpty() || mail.getText().isEmpty() || numtel.getText().isEmpty() 
            || sujet.getText().isEmpty() || msg.getText().isEmpty()) {
        Alert a = new Alert(Alert.AlertType.WARNING, "Tous les champs sont obligatoires", ButtonType.OK);
        a.showAndWait();
        return;
                    }
        reclamation p = new reclamation(np.getText(), mail.getText(),numtel.getText(), sujet.getText(), msg.getText());
        sp.ajouter(p);
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Votre demande a ete envoyer!", ButtonType.OK);
        a.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheDemande.fxml"));
                Parent root = loader.load();
                np.getScene().setRoot(root);          
                GUI.AfficheDemandeController apc = loader.getController();
                apc.setNom(np.getText());
                apc.setEmail(mail.getText());
                apc.setNumtel(numtel.getText());
                apc.setSujet(sujet.getText());
                apc.setMessage(msg.getText());
        
    }
 
    @FXML
    private void handleKeyType() {
        boolean hasInvalidInput = !nomPattern.matcher(np.getText()).matches()
                || !emailPattern.matcher(mail.getText()).matches()
                || !sujetPattern.matcher(sujet.getText()).matches()
                || !messagePattern.matcher(msg.getText()).matches()
                || !numtelPattern.matcher(numtel.getText()).matches();
        btn.setDisable(hasInvalidInput);
        
    }
    }