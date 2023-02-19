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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yadii
 */
public class AfficheDemandeController implements Initializable {

    @FXML
    private Label lblNom;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblNumtel;
    @FXML
    private Label lblSujet;
    @FXML
    private Label lblMessage;
    @FXML
    private Button cancelid;
    reclamation dm = null;
    private int selectedReclamationId;
    @FXML
    private Button modifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setSelectedReclamationId(int selectedReclamationId) {
    this.selectedReclamationId = selectedReclamationId;
    }
    public void setSelectedReclamation(reclamation rec) {
    if (rec != null) {
        selectedReclamationId = rec.getId();
        setNom(rec.getNom());
        setEmail(rec.getEmail());
        setNumtel(rec.getNumtel());
        setSujet(rec.getSujet());
        setMessage(rec.getMessage());
    }
}
     public void setNom(String nom) {
                if (lblNom != null) {
                    lblNom.setText(nom);
                }
    }

        public void setEmail(String email){
            if (lblEmail != null) {
                    lblEmail.setText(email);
                }
        }

        public void setNumtel(String numtel){
            if (lblNumtel != null) {
                    lblNumtel.setText(numtel);
                }
        }

                public void setSujet(String sujet){
             if (lblSujet != null) {
                 lblSujet.setText(sujet);
             }
                }

         public void setMessage(String message){
             if (lblMessage != null) {
                 lblMessage.setText(message);
                }
         }

        @FXML
        private void cancel(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            try {
                Parent root = loader.load();
                lblNom.getScene().setRoot(root);
                Alert a = new Alert(Alert.AlertType.WARNING, "Tous les champs sont obligatoires", ButtonType.OK);
            a.showAndWait();
            } catch (IOException ex) {
                System.out.println("error : "+ex.getMessage());
            }
        }
}